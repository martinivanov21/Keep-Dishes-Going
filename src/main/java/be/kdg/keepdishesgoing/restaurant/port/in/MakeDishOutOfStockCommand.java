package be.kdg.keepdishesgoing.restaurant.port.in;

import java.util.Objects;
import java.util.UUID;

public record MakeDishOutOfStockCommand(UUID restaurantUUID, UUID ownerUUID, UUID dishId) {

    public void MarkDishOutOfStockCommand {
        Objects.requireNonNull(restaurantUUID, "restaurantUUID must not be null");
        Objects.requireNonNull(dishId, "dishId must not be null");
        Objects.requireNonNull(ownerUUID, "ownerUUID must not be null");
    }
}
