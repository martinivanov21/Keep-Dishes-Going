package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;

import java.util.List;

public interface FindAllRestaurantPort {

    List<Restaurant> findAll();
}
