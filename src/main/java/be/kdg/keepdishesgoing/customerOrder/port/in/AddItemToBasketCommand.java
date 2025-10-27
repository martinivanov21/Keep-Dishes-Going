package be.kdg.keepdishesgoing.customerOrder.port.in;

import java.util.UUID;

public record AddItemToBasketCommand(
        UUID dishId,
        int quantity
) {
}
