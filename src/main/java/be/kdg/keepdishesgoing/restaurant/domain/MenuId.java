package be.kdg.keepdishesgoing.restaurant.domain;

import java.util.UUID;

public record MenuId(UUID uuid) {

    public static MenuId of(UUID uuid) {
        return new MenuId(uuid);
    }

    public static MenuId create() {
        return new MenuId(UUID.randomUUID());
    }
}
