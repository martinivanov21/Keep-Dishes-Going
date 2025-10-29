package be.kdg.keepdishesgoing.customerOrder.port.in;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.CheckoutRequest;

import java.util.UUID;

public record CheckoutBasketCommand(
        UUID basketId,
        CheckoutRequest request
) {
}
