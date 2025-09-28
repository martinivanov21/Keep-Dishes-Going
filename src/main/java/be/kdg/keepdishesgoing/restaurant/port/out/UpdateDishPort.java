package be.kdg.keepdishesgoing.restaurant.port.out;

import be.kdg.keepdishesgoing.restaurant.domain.DishId;

public interface UpdateDishPort {
    void updateDish(DishId dishId);
}
