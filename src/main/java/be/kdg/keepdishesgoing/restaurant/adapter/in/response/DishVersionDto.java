package be.kdg.keepdishesgoing.restaurant.adapter.in.response;

import be.kdg.keepdishesgoing.restaurant.domain.enums.DishType;
import be.kdg.keepdishesgoing.restaurant.domain.enums.FoodTag;

import java.time.LocalDateTime;
import java.util.UUID;

public record DishVersionDto(
        UUID dishVersionId,
        String nameOfDish,
        String description,
        double price,
        String picture,
        String preparationTime,
        FoodTag foodTag,
        DishType typeOfDish
) {
}
