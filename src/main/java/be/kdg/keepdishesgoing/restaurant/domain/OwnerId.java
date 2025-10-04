package be.kdg.keepdishesgoing.restaurant.domain;

import java.util.UUID;

public record OwnerId(UUID uuid) {

    public static OwnerId create() {
        return new OwnerId(UUID.randomUUID());
    }
    public static OwnerId of(String uuid) {
        return new OwnerId(UUID.fromString(uuid));
    }
}
