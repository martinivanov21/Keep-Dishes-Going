package be.kdg.keepdishesgoing.restaurant.port.out.dish;

import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;

import java.util.UUID;

public interface CreateDishVersionPort {
    DishVersion save(DishVersion dishVersion);
}
