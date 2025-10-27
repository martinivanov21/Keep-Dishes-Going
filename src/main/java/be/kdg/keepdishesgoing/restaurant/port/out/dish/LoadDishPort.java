package be.kdg.keepdishesgoing.restaurant.port.out.dish;

import be.kdg.keepdishesgoing.restaurant.domain.RestaurantId;
import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.domain.DishId;

import java.util.List;
import java.util.Optional;

public interface LoadDishPort {
    Optional<Dish> loadById(DishId dishId);
    List<Dish> findByRestaurantId(RestaurantId restaurantId);
}
