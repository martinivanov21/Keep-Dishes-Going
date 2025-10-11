package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateRestaurantRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.RestaurantDto;
import be.kdg.keepdishesgoing.restaurant.domain.*;
import be.kdg.keepdishesgoing.restaurant.port.in.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants")
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


    @PostMapping("/create")
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody CreateRestaurantRequest request) {

        var restaurant = new Restaurant(
                RestaurantId.create(),
                request.nameOfRestaurant(),
                request.cuisine(),
                request.openingStatus(),
                request.defaultPreparationTime(),
                request.contactEmail(),
                request.picture(),
                request.addressId() != null ? new AddressId(UUID.fromString(request.addressId())) : null,
                request.ownerId() != null ? new OwnerId(UUID.fromString(request.ownerId())) : null,
                null,
                List.of()
        );

        Restaurant created = createRestaurantUseCase.createRestaurant(
                new CreateRestaurantCommand(restaurant)
        );

        RestaurantDto restaurantDto = new RestaurantDto(
                created.getRestaurantId().uuid(),
                created.getNameOfRestaurant(),
                created.getContactEmail(),
                created.getPicture(),
                created.getCuisine().name(),
                created.getOpeningStatus().name()
        );

        return ResponseEntity.ok(restaurantDto);
    }


    @GetMapping()
    public ResponseEntity<List<RestaurantDto>> findAll() {
        List<RestaurantDto> restaurantDtos = this.findAllRestaurantPort.findAll().stream()
                .map(r -> new RestaurantDto(
                        r.getRestaurantId().uuid(),
                        r.getNameOfRestaurant(),
                        r.getContactEmail(),
                        r.getPicture(),
                        r.getCuisine().name(),
                        r.getOpeningStatus().name()
                )).toList();

        return ResponseEntity.ok(restaurantDtos);
    }

    @PostMapping("/{restaurantId}/dishes/{dishId}/out-of-stock")
    public ResponseEntity<Void> makeDishOutOfStock(
            @PathVariable UUID restaurantId,
            @PathVariable UUID dishId,
            @RequestParam UUID ownerId) {

        var command = new MakeDishOutOfStockCommand(restaurantId, dishId, ownerId);
        makeDishOutOfStockUseCase.makeDishOutOfStock(command);

        return ResponseEntity.noContent().build();
    }

}
