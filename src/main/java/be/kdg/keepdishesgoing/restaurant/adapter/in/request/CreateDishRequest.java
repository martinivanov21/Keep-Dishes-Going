package be.kdg.keepdishesgoing.restaurant.adapter.in.request;

import be.kdg.keepdishesgoing.restaurant.domain.DishId;
import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;
import be.kdg.keepdishesgoing.restaurant.domain.Menu;
import be.kdg.keepdishesgoing.restaurant.domain.enums.DishStatus;


public record CreateDishRequest(
        DishVersion liveVersion,
        DishVersion draftVersion,
        DishStatus status,
        int quantity,
        Menu menu
) {
}
