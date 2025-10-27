package be.kdg.keepdishesgoing.customerOrder.port.out;

import be.kdg.keepdishesgoing.customerOrder.domain.Restaurant;
import be.kdg.keepdishesgoing.customerOrder.domain.RestaurantId;

import java.util.List;
import java.util.Optional;

public interface LoadRestaurantPort {

    List<Restaurant> loadAll();
    Optional<Restaurant> loadById(RestaurantId restaurantId);
}
