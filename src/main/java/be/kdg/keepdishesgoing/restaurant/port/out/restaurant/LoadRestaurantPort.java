package be.kdg.keepdishesgoing.restaurant.port.out.restaurant;

import be.kdg.keepdishesgoing.restaurant.domain.OwnerId;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.domain.RestaurantId;

import java.util.List;
import java.util.Optional;

public interface LoadRestaurantPort {

    Optional<Restaurant> loadBy(OwnerId ownerId);

    List<Restaurant> loadAll();
}
