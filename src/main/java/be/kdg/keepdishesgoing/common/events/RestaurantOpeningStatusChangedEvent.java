package be.kdg.keepdishesgoing.common.events;

import be.kdg.keepdishesgoing.common.events.OpeningStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public record RestaurantOpeningStatusChangedEvent(
        UUID restaurantId,
        String status,
        LocalDateTime eventPit
) implements DomainEvent {

    public RestaurantOpeningStatusChangedEvent {
        Objects.requireNonNull(restaurantId, "restaurantId cannot be null");
        Objects.requireNonNull(status, "status cannot be null");
        if (eventPit == null) {
            eventPit = LocalDateTime.now();
        }
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
