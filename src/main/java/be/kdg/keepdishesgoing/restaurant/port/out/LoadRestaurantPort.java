package be.kdg.keepdishesgoing.restaurant.port.out;

import be.kdg.keepdishesgoing.common.domain.PersonId;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface LoadRestaurantPort {

    Optional<Restaurant> loadBy(PersonId owner);

    List<Restaurant> loadAll();
}
