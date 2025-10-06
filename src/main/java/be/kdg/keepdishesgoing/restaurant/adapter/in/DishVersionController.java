package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateDishVersionRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.request.UpdateDishVersionRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.DishVersionDto;
import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;
import be.kdg.keepdishesgoing.restaurant.domain.DishVersionId;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateDishVersionCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateDishVersionUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.UpdateDishVersionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/dishversions")
public class DishVersionController {

    private static final Logger logger = LoggerFactory.getLogger(DishVersionController.class);
    private final CreateDishVersionUseCase createDishVersionUseCase;
    private final UpdateDishVersionPort updateDishVersionPort;


    public DishVersionController(CreateDishVersionUseCase createDishVersionUseCase, UpdateDishVersionPort updateDishVersionPort) {
        this.createDishVersionUseCase = createDishVersionUseCase;
        this.updateDishVersionPort = updateDishVersionPort;
    }

    @PostMapping("/create")
    public ResponseEntity<DishVersionDto> createDishVersion(@RequestBody CreateDishVersionRequest request) {
        var dishVersionId = DishVersionId.create();

        var command = new CreateDishVersionCommand(
                new DishVersion(dishVersionId,
                        request.nameOfDish(),
                        request.description(),
                        request.price(),
                        request.picture(),
                        request.preparationTime(),
                        request.foodTag(),
                        request.typeOfDish())
        );

        DishVersion newDishVersion = createDishVersionUseCase.create(command);

        return ResponseEntity.ok(toDto(newDishVersion));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<DishVersionDto> updateDishVersion(@PathVariable UUID id,
                                                            @RequestBody UpdateDishVersionRequest request) {
        logger.info("Received request to update DishVersion with id {}", id);

        DishVersion dishVersion = new DishVersion(
                new DishVersionId(id),
                request.nameOfDish(),
                request.description(),
                request.price(),
                request.picture(),
                request.preparationTime(),
                request.foodTag(),
                request.typeOfDish()
        );

        DishVersion updatedDishVersion = updateDishVersionPort.update(dishVersion);

        return ResponseEntity.ok(toDto(updatedDishVersion));
    }




    private DishVersionDto toDto(DishVersion version) {
        return new DishVersionDto(
                version.getDishVersionId().uuid(),
                version.getNameOfDish(),
                version.getDescription(),
                version.getPrice(),
                version.getPicture(),
                version.getPreparationTime(),
                version.getFoodTag(),
                version.getTypeOfDish()
        );
    }



}
