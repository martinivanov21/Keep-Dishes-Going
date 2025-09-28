package be.kdg.keepdishesgoing.restaurant.port.out;

import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.domain.DishId;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface LoadDishPort {
    Optional<Dish> loadDish(DishId dishId);
}
