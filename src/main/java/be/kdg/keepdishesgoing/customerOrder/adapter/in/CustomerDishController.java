package be.kdg.keepdishesgoing.customerOrder.adapter.in;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.DishBrowseDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.DishDetailDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.mapper.DishMapperProjection;
import be.kdg.keepdishesgoing.customerOrder.domain.Dish;
import be.kdg.keepdishesgoing.customerOrder.domain.DishId;
import be.kdg.keepdishesgoing.customerOrder.domain.DishStatus;
import be.kdg.keepdishesgoing.customerOrder.domain.RestaurantId;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadDishPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer-order/restaurants/{restaurantId}/dishes")
public class CustomerDishController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDishController.class);

    private final LoadDishPort loadDishPort;
    private final DishMapperProjection dishMapper;

    public CustomerDishController(LoadDishPort loadDishPort, DishMapperProjection dishMapper) {
        this.loadDishPort = loadDishPort;
        this.dishMapper = dishMapper;
    }


    @GetMapping
    public ResponseEntity<List<DishBrowseDto>> getRestaurantDishes(@PathVariable UUID restaurantId) {
        logger.info("Customer viewing menu from restaurant " + restaurantId);

        List<Dish> dishes = loadDishPort.loadByRestaurantId(RestaurantId.of(restaurantId));

        List<DishBrowseDto> dtos = dishes.stream()
                .filter(dish -> dish.getStatus() == DishStatus.PUBLISHED)
                .map(d -> dishMapper.mapToDishBrowseDto(d))
                .toList();
        return ResponseEntity.ok(dtos);
    }


    @GetMapping("/{dishId}")
    public ResponseEntity<DishDetailDto> getDishDetails(@PathVariable UUID restaurantId, @PathVariable UUID dishId) {
        Dish dish = loadDishPort.loadById(DishId.of(dishId)).orElseThrow();

        if (dish.getStatus() != DishStatus.PUBLISHED) {
            throw new IllegalStateException("Dish status is not PUBLISHED");
        }

        DishDetailDto detailDto = dishMapper.mapToDishDetailDto(dish);
        return ResponseEntity.ok(detailDto);
    }
}
