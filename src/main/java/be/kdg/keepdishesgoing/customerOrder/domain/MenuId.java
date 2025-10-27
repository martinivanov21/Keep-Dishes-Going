package be.kdg.keepdishesgoing.customerOrder.domain;

import java.util.UUID;

public record MenuId(UUID menuId) {
    public static MenuId create() {
        return new MenuId(UUID.randomUUID());
    }
    public static MenuId of(String uuid) {
        return new MenuId(UUID.fromString(uuid));
    }
}
