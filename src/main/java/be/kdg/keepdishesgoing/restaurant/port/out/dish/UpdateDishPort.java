package be.kdg.keepdishesgoing.restaurant.port.out.dish;

import be.kdg.keepdishesgoing.restaurant.domain.DishId;

public interface UpdateDishPort {
    void updateDish(DishId dishId);
}
