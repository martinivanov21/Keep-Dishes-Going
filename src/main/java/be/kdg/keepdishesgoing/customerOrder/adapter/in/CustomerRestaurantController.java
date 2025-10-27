package be.kdg.keepdishesgoing.customerOrder.adapter.in;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.RestaurantBrowseDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.RestaurantDetailDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.mapper.RestaurantMapper;
import be.kdg.keepdishesgoing.customerOrder.domain.Restaurant;
import be.kdg.keepdishesgoing.customerOrder.domain.RestaurantId;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadRestaurantPort;
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
@RequestMapping("/api/customer-order/restaurants")
public class CustomerRestaurantController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRestaurantController.class);

    private final LoadRestaurantPort loadRestaurantPort;
    private final RestaurantMapper restaurantMapper;

    public CustomerRestaurantController(LoadRestaurantPort loadRestaurantPort, RestaurantMapper restaurantMapper) {
        this.loadRestaurantPort = loadRestaurantPort;
        this.restaurantMapper = restaurantMapper;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantBrowseDto>> getAllRestaurants() {
        logger.info("Customer browsing all restaurants");

        List<Restaurant> restaurants = loadRestaurantPort.loadAll();

        List<RestaurantBrowseDto> dtos = restaurants.stream().map(dto -> restaurantMapper.mapToRestaurantBrowseDto(dto)).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDetailDto> getRestaurantDetails(@PathVariable UUID restaurantId) {
        logger.info("Customer browsing restaurant details for restaurant " + restaurantId);

        Restaurant restaurant = loadRestaurantPort.loadById(RestaurantId.of(restaurantId))
                .orElseThrow(()-> new IllegalArgumentException("Restaurant " + restaurantId + " not found"));

        RestaurantDetailDto dto = restaurantMapper.mapToRestaurantDetailDto(restaurant);

        return ResponseEntity.ok(dto);
    }


}
