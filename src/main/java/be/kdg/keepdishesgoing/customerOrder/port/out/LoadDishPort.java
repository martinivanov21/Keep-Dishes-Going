package be.kdg.keepdishesgoing.customerOrder.port.out;

import be.kdg.keepdishesgoing.customerOrder.domain.Dish;
import be.kdg.keepdishesgoing.customerOrder.domain.DishId;
import be.kdg.keepdishesgoing.customerOrder.domain.RestaurantId;

import java.util.List;
import java.util.Optional;

public interface LoadDishPort {
    Optional<Dish> loadById(DishId dishId);
    List<Dish> loadAll();
    List<Dish> loadByRestaurantId(RestaurantId restaurantId);
}
