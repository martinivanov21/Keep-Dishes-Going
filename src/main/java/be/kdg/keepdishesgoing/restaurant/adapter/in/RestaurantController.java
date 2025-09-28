package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.response.RestaurantDto;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.port.in.FindAllRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.in.MakeDishOutOfStockCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.MakeDishOutOfStockUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final FindAllRestaurantPort findAllRestaurantPort;
    private final MakeDishOutOfStockUseCase makeDishOutOfStockUseCase;

    public RestaurantController(FindAllRestaurantPort findAllRestaurantPort,
                                MakeDishOutOfStockUseCase makeDishOutOfStockUseCase) {
        this.findAllRestaurantPort = findAllRestaurantPort;
        this.makeDishOutOfStockUseCase = makeDishOutOfStockUseCase;
    }

//    MAP LATER THE ROWS
    @GetMapping
    public ResponseEntity<List<RestaurantDto>> findAll() {
        List<RestaurantDto> restaurantDtos = this.findAllRestaurantPort.findAll().stream()
                .map(restaurant -> new RestaurantDto()).toList();

        return ResponseEntity.ok(restaurantDtos);
    }

    @PostMapping("/{restaurantId}/dishes/{dishId}/out-of-stock")
    public ResponseEntity<Void> makeDishOutOfStock(
            @PathVariable UUID restaurantId, @PathVariable UUID dishId, @RequestParam UUID ownerId) {

        var command = new MakeDishOutOfStockCommand(restaurantId, dishId, ownerId);
        makeDishOutOfStockUseCase.makeDishOutOfStock(command);

        return ResponseEntity.noContent().build();
    }

}
