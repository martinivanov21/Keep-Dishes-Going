package be.kdg.keepdishesgoing.restaurant.port.out.dish;

import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;

import java.util.UUID;

public interface UpdateDishVersionPort {
    DishVersion update(DishVersion dishVersion);
}
