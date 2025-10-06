package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;

public interface CreateDishVersionUseCase {

    DishVersion create(CreateDishVersionCommand command);

}
