package be.kdg.keepdishesgoing.restaurant.port.out.dish;

import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.domain.DishId;

import java.util.Optional;

public interface LoadDishPort {
    Optional<Dish> loadDish(DishId dishId);
}
