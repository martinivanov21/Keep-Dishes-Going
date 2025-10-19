package be.kdg.keepdishesgoing.common.events;

import java.time.LocalDateTime;

public record CreateRestaurantEvent() implements DomainEvent {
    @Override
    public LocalDateTime eventPit() {
        return null;
    }
}
