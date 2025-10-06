package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;

public interface UpdateDishVersionUseCase {
    DishVersion update(UpdateDishVersionCommand updateDishVersionCommand);
}
