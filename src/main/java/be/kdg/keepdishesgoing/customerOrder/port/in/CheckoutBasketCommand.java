package be.kdg.keepdishesgoing.customerOrder.port.in;

import java.util.UUID;

public record CheckoutBasketCommand(
        UUID basketId,
        String street,
        String number,
        String city
) {
}
