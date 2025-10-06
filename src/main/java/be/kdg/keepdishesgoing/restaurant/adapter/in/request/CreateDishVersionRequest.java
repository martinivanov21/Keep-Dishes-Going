package be.kdg.keepdishesgoing.restaurant.adapter.in.request;

import be.kdg.keepdishesgoing.restaurant.domain.enums.DishType;
import be.kdg.keepdishesgoing.restaurant.domain.enums.FoodTag;

import java.time.LocalDateTime;


public record CreateDishVersionRequest(
        String nameOfDish,
        String description,
        double price,
        String picture,
        String preparationTime,
        FoodTag foodTag,
        DishType typeOfDish
) {
}
