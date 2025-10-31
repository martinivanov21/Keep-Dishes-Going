package be.kdg.keepdishesgoing.common.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerOrderItemQuantityChangeEvent(
        UUID customerOrderId,
        UUID restaurantId,
        UUID dishId,
        int newQuantity,
        BigDecimal lineTotal,
        BigDecimal orderTotal,
        LocalDateTime occurredOn
) implements DomainEvent {
    @Override
    public LocalDateTime eventPit() {
        return null;
    }
}
