package be.kdg.keepdishesgoing.customerOrder.adapter.in.response;

import java.util.UUID;

public record UpdateBasketItemRequest(
        UUID dishId,
        int quantity
) {
    public UpdateBasketItemRequest {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }

}
