package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.response.CreateRestaurantRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.RestaurantDto;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.port.in.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final FindAllRestaurantPort findAllRestaurantPort;
    private final MakeDishOutOfStockUseCase makeDishOutOfStockUseCase;
    private final CreateRestaurantUseCase createRestaurantUseCase;

    public RestaurantController(FindAllRestaurantPort findAllRestaurantPort,
                                MakeDishOutOfStockUseCase makeDishOutOfStockUseCase, CreateRestaurantUseCase createRestaurantUseCase) {
        this.findAllRestaurantPort = findAllRestaurantPort;
        this.makeDishOutOfStockUseCase = makeDishOutOfStockUseCase;
        this.createRestaurantUseCase = createRestaurantUseCase;
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

    @PostMapping("/create")
    public ResponseEntity<RestaurantDto> saveRestaurant(@RequestBody CreateRestaurantRequest request) {

        var command = new CreateRestaurantCommand(
                request.name(),
                request.contactEmail(),
                request.picture(),
                request.street(),
                request.city(),
                request.postalCode(),
                request.number(),
                request.country(),
                request.defaultPreparationTime(),
                request.cuisine(),
                request.openingHours(),
                request.closingHours(),
                request.ownerId()
        );

        Restaurant restaurant = createRestaurantUseCase.createRestaurant(command);

        RestaurantDto restaurantDto = null;
//        = new RestaurantDto(
//                restaurant.getRestaurantId().toString(),
//                restaurant.getNameOfRestaurant(),
//                restaurant.getOwner().getUsername(),
//                restaurant.getContactEmail(),
//                restaurant.getPicture(),
//                restaurant.getCuisine().name(),
//                restaurant.getOpeningHours().toString(),
//                restaurant.getClosingHours().toString()
//        );

        return ResponseEntity.ok(restaurantDto);
    }

}
