package be.kdg.keepdishesgoing.restaurant.domain;

import java.util.UUID;

public record RestaurantId(UUID uuid) {

    public RestaurantId create() {
        return new RestaurantId(UUID.randomUUID());
    }

    public static RestaurantId of(UUID uuid) {
        return new RestaurantId(uuid);
    }
}
