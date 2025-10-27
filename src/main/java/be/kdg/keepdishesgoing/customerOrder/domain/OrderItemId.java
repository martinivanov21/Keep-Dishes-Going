package be.kdg.keepdishesgoing.customerOrder.domain;

import java.util.UUID;

public record OrderItemId(UUID uuid) {

    public static OrderItemId create() {
        return new OrderItemId(UUID.randomUUID());
    }
    public static OrderItemId of(String uuid) {
        return new OrderItemId(UUID.fromString(uuid));
    }
}
