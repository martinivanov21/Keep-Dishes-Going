package be.kdg.keepdishesgoing.restaurant.port.out.restaurant;

import be.kdg.keepdishesgoing.restaurant.domain.RestaurantOrder;

public interface SaveRestaurantOrderPort {
    RestaurantOrder save(RestaurantOrder order);
}
