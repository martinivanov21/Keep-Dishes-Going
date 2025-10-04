package be.kdg.keepdishesgoing.restaurant.port.out.restaurant;

import be.kdg.keepdishesgoing.restaurant.domain.RestaurantId;

public interface DeleteRestaurantPort {
    void delete(RestaurantId restaurantId);
}
