package be.kdg.keepdishesgoing.restaurant.adapter.out;

import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.domain.RestaurantId;
import be.kdg.keepdishesgoing.restaurant.port.out.LoadRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.SaveRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.UpdateRestaurantPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantJpaAdapter implements LoadRestaurantPort, UpdateRestaurantPort, SaveRestaurantPort {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantJpaAdapter.class);

    // TO DO
//    private final RestaurantJpaRepository restaurants;
//
//    public RestaurantJpaAdapter(RestaurantJpaRepository restaurants) {
//        this.restaurants = restaurants;
//    }

    @Override
    public Optional<Restaurant> loadBy(RestaurantId restaurantId) {
        return Optional.empty();
    }

    @Override
    public List<Restaurant> loadAll() {
        return List.of();
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return null;
    }
}
