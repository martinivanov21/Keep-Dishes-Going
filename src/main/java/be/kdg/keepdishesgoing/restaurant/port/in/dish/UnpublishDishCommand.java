package be.kdg.keepdishesgoing.restaurant.port.in.dish;

import be.kdg.keepdishesgoing.restaurant.domain.DishId;

public record UnpublishDishCommand(DishId dishId) {
}
