package be.kdg.keepdishesgoing.restaurant.port.in.dish;


import be.kdg.keepdishesgoing.restaurant.domain.Dish;

public interface PublishDishUseCase {
    Dish publishDish(PublishDishCommand command);
}
