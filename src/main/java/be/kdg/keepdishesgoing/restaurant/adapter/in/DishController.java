package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateDishRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.DishDto;
import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.domain.DishId;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateDishCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateDishUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/dishes")
public class DishController {

    private final CreateDishUseCase createDishUseCase;

    public DishController(CreateDishUseCase createDishUseCase) {
        this.createDishUseCase = createDishUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<DishDto>  createDish(@RequestBody CreateDishRequest request) {

        var dishId = DishId.create();

        var command = new CreateDishCommand(
                new Dish(dishId,request.liveVersion(),
                        request.draftVersion(),request.status(),request.quantity(),
                        request.menu())
        );

        Dish newDish = createDishUseCase.createDish(command);

        return ResponseEntity.ok(new DishDto(
                newDish.getDishId().uuid(),
                newDish.getLiveVersion(),
                newDish.getDraftVersion(),
                newDish.getStatus(),
                newDish.getQuantity(),
                newDish.getMenu()
        ));
    }
}
