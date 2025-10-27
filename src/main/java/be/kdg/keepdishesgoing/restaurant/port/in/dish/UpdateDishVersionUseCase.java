package be.kdg.keepdishesgoing.restaurant.port.in.dish;

import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;

public interface UpdateDishVersionUseCase {
    DishVersion update(UpdateDishVersionCommand updateDishVersionCommand);
}
