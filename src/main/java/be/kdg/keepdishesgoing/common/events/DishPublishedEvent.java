package be.kdg.keepdishesgoing.common.events;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public record DishPublishedEvent(
        UUID dishId,
        UUID restaurantId,
        String nameOfDish,
        String description,
        double price,
        String pictureUrl,
        String preparationTime,
        String foodTag,
        String dishType,
        int quantity,
        LocalDateTime eventPit
) implements DomainEvent {

    public DishPublishedEvent {
        Objects.requireNonNull(dishId, "dishId cannot be null");
        Objects.requireNonNull(restaurantId, "restaurantId cannot be null");
        Objects.requireNonNull(nameOfDish, "nameOfDish cannot be null");
        if (eventPit == null) {
            eventPit = LocalDateTime.now();
        }
    }
    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
