package be.kdg.keepdishesgoing.common.domain;

import be.kdg.keepdishesgoing.restaurant.domain.RestaurantId;

import java.util.UUID;

public record AddressId(UUID uuid) {

    public AddressId create() {
        return new AddressId(UUID.randomUUID());
    }
    public static AddressId of(UUID uuid) {
        return new AddressId(uuid);
    }
}
