package be.kdg.keepdishesgoing.restaurant.domain;

import java.util.UUID;

public record AddressId(UUID uuid) {

    public AddressId create() {
        return new AddressId(UUID.randomUUID());
    }
    public static AddressId of(String uuid) {
        return new AddressId(UUID.fromString(uuid));
    }
}
