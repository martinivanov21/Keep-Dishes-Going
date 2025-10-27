package be.kdg.keepdishesgoing.customerOrder.adapter.in.response;

import java.util.UUID;

public record DishBrowseDto(
        UUID dishId,
        String name,
        String description,
        double price,
        String pictureUrl,
        String preparationTime,
        String foodTag,
        String dishType,
        boolean isAvailable
) {
}
