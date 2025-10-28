package be.kdg.keepdishesgoing.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record MenuUpdatedEvent(
        UUID menuId,
        UUID restaurantId,
        LocalDateTime eventPit
) implements DomainEvent {

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
