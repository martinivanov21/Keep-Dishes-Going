package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateRestaurantRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.MenuDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.RestaurantDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.ScheduleHourDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.WorkingHourDto;
import be.kdg.keepdishesgoing.restaurant.adapter.out.embeded.WorkingHourEmbeddable;
import be.kdg.keepdishesgoing.restaurant.domain.*;
import be.kdg.keepdishesgoing.restaurant.port.in.*;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.MakeDishOutOfStockCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.MakeDishOutOfStockUseCase;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

        List<WorkingHour> hours = new ArrayList<>();
        if (request.workingHours() != null) {
            request.workingHours().forEach((day, interval) -> {
                LocalTime open  = LocalTime.parse(interval.openHour());
                LocalTime close = LocalTime.parse(interval.closeHour());
                if (!open.isBefore(close)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Opening time must be before closing time for " + day);
                }
                hours.add(new WorkingHour(day, open, close));
            });
            hours.sort(Comparator.comparing(WorkingHour::getDayOfWeek));
        }

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
                request.menuId() != null ? new MenuId(request.menuId().uuid()) : null,
                hours
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
                                .map(wh -> new WorkingHourDto(
                                        wh.getDayOfWeek().name(),
                                        wh.getOpeningTime().toString(),
                                        wh.getClosingTime().toString()
                                )).toList(),
                created.getOwnerId().uuid(),
                created.getMenuId() != null ? new MenuDto(created.getMenuId().uuid(),
                        created.getRestaurantId().uuid()) : null
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
                                .map(work -> new WorkingHourDto(
                                        work.getDayOfWeek().name(),
                                        work.getOpeningTime().toString(),
                                        work.getClosingTime().toString()
                                )).toList(),
                        r.getOwnerId().uuid(),
                        new MenuDto(r.getMenuId().uuid(),
                                r.getRestaurantId().uuid())

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
