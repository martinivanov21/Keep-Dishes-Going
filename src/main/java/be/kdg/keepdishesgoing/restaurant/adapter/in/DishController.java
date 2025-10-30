package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateDishDraftRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateDishRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.request.UpdateDishVersionRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.DishDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.DishVersionDto;
import be.kdg.keepdishesgoing.restaurant.adapter.mapper.DishMapper;
import be.kdg.keepdishesgoing.restaurant.domain.*;
import be.kdg.keepdishesgoing.restaurant.domain.enums.DishStatus;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.*;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.LoadDishPort;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.UpdateDishVersionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/dishes")
public class DishController {

    private static final Logger logger = LoggerFactory.getLogger(DishController.class);

    private final CreateDishDraftUseCase createDishDraftUseCase;
    private final PublishDishUseCase publishDishUseCase;
    private final UnpublishDishUseCase unpublishDishUseCase;
    private final UpdateDishVersionUseCase updateDishVersionUseCase;
    private final MakeDishOutOfStockUseCase makeDishOutOfStockUseCase;
    private final LoadDishPort loadDishPort;
    private final DishMapper dishMapper;
    private final UpdateDishDraftUseCase updateDishDraftUseCase;

    public DishController(CreateDishDraftUseCase createDishDraftUseCase, PublishDishUseCase publishDishUseCase,
                          UnpublishDishUseCase unpublishDishUseCase, UpdateDishVersionUseCase updateDishVersionUseCase, MakeDishOutOfStockUseCase makeDishOutOfStockUseCase, LoadDishPort loadDishPort, DishMapper dishMapper, UpdateDishDraftUseCase updateDishDraftUseCase) {
        this.createDishDraftUseCase = createDishDraftUseCase;
        this.publishDishUseCase = publishDishUseCase;
        this.unpublishDishUseCase = unpublishDishUseCase;
        this.updateDishVersionUseCase = updateDishVersionUseCase;
        this.makeDishOutOfStockUseCase = makeDishOutOfStockUseCase;
        this.loadDishPort = loadDishPort;
        this.dishMapper = dishMapper;
        this.updateDishDraftUseCase = updateDishDraftUseCase;
    }


    @PostMapping("/create-draft")
    public ResponseEntity<DishDto>  createDishDraft(@PathVariable UUID restaurantId,
                                                    @RequestBody CreateDishDraftRequest request) {

        logger.info("Creating Draft dish {}", request.nameOfDish());

        DishVersion draftVersion = new DishVersion(
                DishVersionId.create(),
                request.nameOfDish(),
                request.description(),
                request.price(),
                request.picture(),
                request.preparationTime(),
                request.foodTag(),
                request.typeOfDish()
        );
        Dish dish = new Dish(
                DishId.create(),
                null,
                draftVersion,
                DishStatus.UNPUBLISHED,
                request.quantity(),
                new Menu(MenuId.of(request.menuId()), RestaurantId.of(restaurantId), List.of())
        );

        CreateDishDraftCommand command = new CreateDishDraftCommand(dish);

        Dish created = createDishDraftUseCase.createDraft(command);
        DishDto dto = dishMapper.mapToDishDto(created);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{dishId}/publish")
    public ResponseEntity<DishDto> publishDish( @PathVariable UUID restaurantId,
                                                @PathVariable UUID dishId) {
        logger.info("Publishing Dish {}", dishId);

        PublishDishCommand command = new PublishDishCommand( new DishId(dishId));
        Dish published = publishDishUseCase.publishDish(command);

        DishDto dto = dishMapper.mapToDishDto(published);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{dishId}/unpublish")
    public ResponseEntity<DishDto> unpublishDish( @PathVariable UUID restaurantId,
                                                @PathVariable UUID dishId) {
        logger.info("Unpublishing Dish {}", dishId);

        UnpublishDishCommand command = new UnpublishDishCommand( new DishId(dishId));
        Dish unpublishDish = unpublishDishUseCase.unpublishDish(command);

        DishDto dto = dishMapper.mapToDishDto(unpublishDish);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{dishId}/draft")
    public ResponseEntity<DishDto> updateDraft(@PathVariable UUID restaurantId,
                                                  @PathVariable UUID dishId,
                                                @RequestBody UpdateDishVersionRequest request) {

        logger.info("Updating draft for dish: {}", dishId);

        Dish existingDish = loadDishPort.loadById(new DishId(dishId))
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        DishVersion updatedDraftVersion = new DishVersion(
                existingDish.getDraftVersion().getDishVersionId(),
                request.nameOfDish(),
                request.description(),
                request.price(),
                request.picture(),
                request.preparationTime(),
                request.foodTag(),
                request.typeOfDish()
        );

        existingDish.updateDraft(updatedDraftVersion);

        UpdateDishDraftCommand command = new UpdateDishDraftCommand(existingDish);
        Dish updated = updateDishDraftUseCase.updateDraft(command);

        DishDto dto = dishMapper.mapToDishDto(updated);

        return ResponseEntity.ok(dto);

    }
    @GetMapping
    public ResponseEntity<List<DishDto>> getAllDishes(@PathVariable UUID restaurantId) {

        logger.info("Getting all dishes for restaurant {}", restaurantId);

        List<Dish> dishes = loadDishPort.findByRestaurantId(RestaurantId.of(restaurantId));

        List<DishDto> dtos = dishes.stream().map(dish -> dishMapper.mapToDishDto(dish)).toList();

        return ResponseEntity.ok(dtos);

    }

    @PostMapping("/{dishId}/out-of-stock")
    public ResponseEntity<Void> makeDishOutOfStock(@PathVariable UUID restaurantId,
                                                   @PathVariable UUID dishId) {

        var command = new MakeDishOutOfStockCommand(restaurantId, dishId);
        makeDishOutOfStockUseCase.makeDishOutOfStock(command);

        return ResponseEntity.noContent().build();

    }




}
