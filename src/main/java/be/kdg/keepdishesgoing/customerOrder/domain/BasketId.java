package be.kdg.keepdishesgoing.customerOrder.domain;

import java.util.UUID;

public record BasketId(UUID uuid) {

    public static BasketId create() {
        return new BasketId(UUID.randomUUID());
    }
    public static BasketId of(String uuid) {
        return new BasketId(UUID.fromString(uuid));
    }
}
