package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateRestaurantRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.request.UpdateRestaurantRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.MenuDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.RestaurantDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.WorkingHourDto;
import be.kdg.keepdishesgoing.restaurant.adapter.mapper.RestaurantOwnerMapper;
import be.kdg.keepdishesgoing.restaurant.adapter.out.owner.OwnerJpaAdapter;
import be.kdg.keepdishesgoing.restaurant.domain.*;
import be.kdg.keepdishesgoing.restaurant.port.in.*;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.MakeDishOutOfStockCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.MakeDishOutOfStockUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.LoadRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.UpdateRestaurantPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/restaurants")
@PreAuthorize("hasRole('OWNER')")
public class RestaurantController {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);
    private final LoadRestaurantPort loadRestaurantPort;
    private final UpdateRestaurantUseCase updateRestaurantUseCase;
    private final MakeDishOutOfStockUseCase makeDishOutOfStockUseCase;
    private final CreateRestaurantUseCase createRestaurantUseCase;
    private final RestaurantOwnerMapper mapper;
    private final OwnerJpaAdapter ownerProvisioningService;


    public RestaurantController(LoadRestaurantPort loadRestaurantPort, UpdateRestaurantUseCase updateRestaurantUseCase,
                                MakeDishOutOfStockUseCase makeDishOutOfStockUseCase, CreateRestaurantUseCase createRestaurantUseCase, RestaurantOwnerMapper mapper, OwnerJpaAdapter ownerProvisioningService) {
        this.loadRestaurantPort = loadRestaurantPort;
        this.updateRestaurantUseCase = updateRestaurantUseCase;
        this.makeDishOutOfStockUseCase = makeDishOutOfStockUseCase;
        this.createRestaurantUseCase = createRestaurantUseCase;
        this.mapper = mapper;
        this.ownerProvisioningService = ownerProvisioningService;
    }


    @GetMapping("/{ownerId}/restaurant")
    @PreAuthorize("#ownerId.toString() == (authentication.token.claims['owner_id'] ?: authentication.token.subject)")
    public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable UUID ownerId) {
        var restaurant = loadRestaurantPort.loadBy(new OwnerId(ownerId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No restaurant for this owner."));
        return ResponseEntity.ok(mapper.toDto(restaurant));
    }

    @PutMapping("/{ownerId}/restaurant")
    @Transactional
    @PreAuthorize("#ownerId.toString() == (authentication.token.claims['owner_id'] ?: authentication.token.subject)")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable UUID ownerId,
                                                          @RequestBody UpdateRestaurantRequest req) {
        var saved = updateRestaurantUseCase.update(new UpdateRestaurantCommand(
                ownerId,
                req.nameOfRestaurant(),
                req.cuisine(),
                req.openingStatus(),
                req.defaultPreparationTime(),
                req.contactEmail(),
                req.picture(),
                req.workingHours()
        ));
        return ResponseEntity.ok(mapper.toDto(saved));
    }




    @PostMapping("/create")
    @Transactional
    public ResponseEntity<RestaurantDto> createRestaurant(@AuthenticationPrincipal Jwt jwt,
                                                          @RequestBody CreateRestaurantRequest request) {
        UUID ownerUuid = jwt.getClaimAsString("owner_id") != null
                ? UUID.fromString(jwt.getClaimAsString("owner_id"))
                : UUID.fromString(jwt.getSubject());

        String email = jwt.getClaimAsString("email");

        ownerProvisioningService.ensureOwnerExists(ownerUuid, email);


        logger.info("createRestaurant ownerUuid={}", ownerUuid);

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
                new OwnerId(ownerUuid),
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
        List<RestaurantDto> restaurantDtos = loadRestaurantPort.loadAll().stream()
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



}
