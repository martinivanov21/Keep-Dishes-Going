package be.kdg.keepdishesgoing.common.events;

import be.kdg.keepdishesgoing.restaurant.adapter.in.response.ScheduleHourDto;
import be.kdg.keepdishesgoing.restaurant.domain.Address;
import be.kdg.keepdishesgoing.restaurant.domain.enums.Cuisine;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record RestaurantCreateEvent(
        UUID restaurantId,
        String restaurantName,
        String pictureUrl,
        String cuisine,
        int defaultPreparationTime,
        String contactEmail,

        String deliveryStreet,
        int deliveryNumber,
        String deliveryCity,

        List<ScheduleHourDto> workingHours,
        UUID menuId,
        LocalDateTime eventPit
) implements DomainEvent {

    public RestaurantCreateEvent {
        Objects.requireNonNull(restaurantId, "restaurantId can not be null");
        Objects.requireNonNull(restaurantName, "restaurantName can not be null");
        if (eventPit == null) {
            eventPit = LocalDateTime.now();
        }
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
