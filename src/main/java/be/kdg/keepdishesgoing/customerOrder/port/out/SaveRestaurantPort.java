package be.kdg.keepdishesgoing.customerOrder.port.out;

import be.kdg.keepdishesgoing.customerOrder.domain.Restaurant;

public interface SaveRestaurantPort {
    Restaurant saveRestaurant(Restaurant restaurant);
}
