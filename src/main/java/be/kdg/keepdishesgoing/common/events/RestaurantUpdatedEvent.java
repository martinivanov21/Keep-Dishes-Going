package be.kdg.keepdishesgoing.common.events;

import java.time.LocalDateTime;

public record RestaurantUpdatedEvent() implements DomainEvent {

    @Override
    public LocalDateTime eventPit() {
        return null;
    }
}
