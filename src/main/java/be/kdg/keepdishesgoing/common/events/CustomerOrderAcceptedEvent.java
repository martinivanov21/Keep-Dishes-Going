package be.kdg.keepdishesgoing.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerOrderAcceptedEvent(
        UUID customerOrderId,
        UUID restaurantId,
        int finalEstimatedTime,
        LocalDateTime occurredOn
) implements DomainEvent {
    @Override
    public LocalDateTime eventPit() {
        return null;
    }
}
