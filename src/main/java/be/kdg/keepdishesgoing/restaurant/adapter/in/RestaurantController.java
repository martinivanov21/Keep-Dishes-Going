package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateRestaurantRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.MenuDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.RestaurantDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.ScheduleHourDto;
import be.kdg.keepdishesgoing.restaurant.domain.*;
import be.kdg.keepdishesgoing.restaurant.port.in.*;
import jakarta.transaction.Transactional;
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
    @Transactional
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
                request.menuId() != null ? new Menu(request.menuId(), List.of()) : null,
                request.workingHours() != null ? request.workingHours().stream()
                                .map(wh -> new ScheduleHour(
                                        wh.getScheduleHourId(),
                                        wh.getDayOfWeek(),
                                        wh.getOpeningTime(),
                                        wh.getClosingTime()
                                )).toList() : List.of()
        );

        Restaurant created = createRestaurantUseCase.createRestaurant(
                new CreateRestaurantCommand(restaurant)
        );

        RestaurantDto restaurantDto = new RestaurantDto(
                created.getRestaurantId().uuid(),
                created.getNameOfRestaurant(),
                created.getCuisine().name(),
                created.getDefaultPreparationTime(),
                created.getContactEmail(),
                created.getPicture(),
                created.getOpeningStatus().name(),
                created.getAddressId().uuid(),
                created.getWorkingHours().stream()
                                .map(wh -> new ScheduleHourDto(
                                        wh.getScheduleHourId(),
                                        wh.getDayOfWeek(),
                                        wh.getOpeningTime(),
                                        wh.getClosingTime()
                                )).toList(),
                created.getOwnerId().uuid(),
                created.getMenu() != null ? new MenuDto(created.getMenu().getMenuId().uuid()) : null
        );

        return ResponseEntity.ok(restaurantDto);
    }


    @GetMapping()
    public ResponseEntity<List<RestaurantDto>> findAll() {
        List<RestaurantDto> restaurantDtos = this.findAllRestaurantPort.findAll().stream()
                .map(r -> new RestaurantDto(
                        r.getRestaurantId().uuid(),
                        r.getNameOfRestaurant(),
                        r.getCuisine().name(),
                        r.getDefaultPreparationTime(),
                        r.getContactEmail(),
                        r.getPicture(),
                        r.getOpeningStatus().name(),
                        r.getAddressId().uuid(),
                        r.getWorkingHours().stream()
                                .map(work -> new ScheduleHourDto(
                                        work.getScheduleHourId(),
                                        work.getDayOfWeek(),
                                        work.getOpeningTime(),
                                        work.getClosingTime()
                                )).toList(),
                        r.getOwnerId().uuid(),
                        new MenuDto(r.getMenu().getMenuId().uuid())

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
