package be.kdg.keepdishesgoing.restaurant.adapter.out;

import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.domain.RestaurantId;
import be.kdg.keepdishesgoing.restaurant.port.out.LoadRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.SaveRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.UpdateRestaurantPort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Component
//public class RestaurantPublisher implements LoadRestaurantPort, UpdateRestaurantPort, SaveRestaurantPort {
//
//
//    @Override
//    public Optional<Restaurant> loadBy(RestaurantId restaurantId) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Restaurant> loadAll() {
//        return List.of();
//    }
//
//    @Override
//    public Restaurant saveRestaurant(Restaurant restaurant) {
//        return null;
//    }
//
//    @Override
//    public Restaurant update(Restaurant restaurant) {
//        return null;
//    }
//}
