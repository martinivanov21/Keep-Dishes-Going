package be.kdg.keepdishesgoing.customerOrder.port.in;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.AddToBasketRequest;

import java.util.UUID;

public record AddItemToBasketCommand(
        UUID basketId,
        AddToBasketRequest request
) {
}
