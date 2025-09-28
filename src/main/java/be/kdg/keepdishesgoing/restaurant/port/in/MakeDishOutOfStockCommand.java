package be.kdg.keepdishesgoing.restaurant.port.in;

import java.util.Objects;
import java.util.UUID;

public record MakeDishOutOfStockCommand(UUID restaurantUUID, UUID ownerUUID, UUID dishId) {
}
