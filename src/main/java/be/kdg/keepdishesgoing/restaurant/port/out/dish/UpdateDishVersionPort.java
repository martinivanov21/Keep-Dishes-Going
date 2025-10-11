package be.kdg.keepdishesgoing.restaurant.port.out.dish;

import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;


public interface UpdateDishVersionPort {
    DishVersion update(DishVersion dishVersion);
}
