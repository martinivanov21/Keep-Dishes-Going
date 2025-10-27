package be.kdg.keepdishesgoing.restaurant.adapter.in.request;

import be.kdg.keepdishesgoing.restaurant.domain.enums.DishType;
import be.kdg.keepdishesgoing.restaurant.domain.enums.FoodTag;

public record UpdateDishDraftRequest(
        String nameOfDish,
        String description,
        double price,
        String picture,
        String preparationTime,
        FoodTag foodTag,
        DishType typeOfDish,
        int quantity
) {
}
