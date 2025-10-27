package be.kdg.keepdishesgoing.restaurant.port.in.dish;

import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;

public record UpdateDishVersionCommand(DishVersion dishVersion) {
}
