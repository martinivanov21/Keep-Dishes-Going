package be.kdg.keepdishesgoing.restaurant.adapter.in.response;

import be.kdg.keepdishesgoing.restaurant.domain.DishId;
import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;
import be.kdg.keepdishesgoing.restaurant.domain.Menu;
import be.kdg.keepdishesgoing.restaurant.domain.enums.DishStatus;

import java.util.UUID;

public record DishDto(
        UUID dishId,
        DishVersionDto liveVersion,
        DishVersionDto draftVersion,
        DishStatus status,
        int quantity,
        Menu menu
) {
}
