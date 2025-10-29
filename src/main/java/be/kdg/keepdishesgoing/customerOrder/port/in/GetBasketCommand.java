package be.kdg.keepdishesgoing.customerOrder.port.in;

import java.util.UUID;

public record GetBasketCommand(
        UUID basketId
) {
}
