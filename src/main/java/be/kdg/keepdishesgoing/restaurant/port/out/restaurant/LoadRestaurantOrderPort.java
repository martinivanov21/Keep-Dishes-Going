package be.kdg.keepdishesgoing.restaurant.port.out.restaurant;

import be.kdg.keepdishesgoing.restaurant.domain.RestaurantOrder;

import java.util.Optional;
import java.util.UUID;

public interface LoadRestaurantOrderPort {
    Optional<RestaurantOrder> load(UUID customerOrderId);
}
