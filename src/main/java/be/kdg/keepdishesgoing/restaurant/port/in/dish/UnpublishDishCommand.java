package be.kdg.keepdishesgoing.restaurant.port.in.dish;

import be.kdg.keepdishesgoing.restaurant.domain.DishId;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;

import java.util.UUID;

public record UnpublishDishCommand(UUID restaurantId, DishId dishId) {
}
