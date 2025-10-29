package be.kdg.keepdishesgoing.customerOrder.port.in;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.UpdateBasketItemRequest;

import java.util.UUID;

public record UpdateBasketItemCommand(
        UUID basketId, UpdateBasketItemRequest request
) {
}
