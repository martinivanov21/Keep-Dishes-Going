package be.kdg.keepdishesgoing.customerOrder.adapter.in.response;

import java.util.UUID;

public record AddToBasketRequest(
        UUID dishId,
        int quantity
) {
    public AddToBasketRequest {
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity cannot be negative");
        }
    }
}
