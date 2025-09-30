package be.kdg.keepdishesgoing.restaurant.domain;

import java.util.UUID;

public record DishVersionId(UUID uuid) {
    public static DishVersionId of(UUID uuid) {
        return new DishVersionId(uuid);
    }

    public static DishVersionId create() {
        return new DishVersionId(UUID.randomUUID());
    }
}
