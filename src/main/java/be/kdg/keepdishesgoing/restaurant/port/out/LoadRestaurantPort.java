package be.kdg.keepdishesgoing.restaurant.port.out;

import be.kdg.keepdishesgoing.common.domain.PersonId;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.domain.RestaurantId;
import org.springframework.stereotype.Component;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

public interface LoadRestaurantPort {

    Optional<Restaurant> loadBy(RestaurantId restaurantId);

    List<Restaurant> loadAll();
}
