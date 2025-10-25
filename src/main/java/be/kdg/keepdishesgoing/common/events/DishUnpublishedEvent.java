package be.kdg.keepdishesgoing.common.events;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public record DishUnpublishedEvent(
        UUID dishId,
        UUID restaurantId,
        LocalDateTime eventPit
) implements DomainEvent {

    public DishUnpublishedEvent {
        Objects.requireNonNull(dishId, "dishId cannot be null");
        Objects.requireNonNull(restaurantId, "restaurantId cannot be null");
        if (eventPit == null) {
            eventPit = LocalDateTime.now();
        }
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
