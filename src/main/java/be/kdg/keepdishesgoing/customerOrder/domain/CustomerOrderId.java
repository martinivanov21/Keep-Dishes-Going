package be.kdg.keepdishesgoing.customerOrder.domain;


import java.util.UUID;

public record CustomerOrderId(UUID uuid) {

    public static CustomerOrderId create() {
        return new CustomerOrderId(UUID.randomUUID());
    }

    public static CustomerOrderId of(UUID uuid) {
        return new CustomerOrderId(uuid);
    }
}
