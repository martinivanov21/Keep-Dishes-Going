package be.kdg.keepdishesgoing.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerOrderDeclinedEvent(
        UUID customerOrderId,
        UUID restaurantId,
        LocalDateTime occurredOn
) {
}
