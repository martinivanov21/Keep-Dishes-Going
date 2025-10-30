package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.request.DayIntervalRequest;
import be.kdg.keepdishesgoing.restaurant.domain.enums.Cuisine;
import be.kdg.keepdishesgoing.restaurant.domain.enums.OpeningStatus;

import java.time.DayOfWeek;
import java.util.Map;
import java.util.UUID;

public record UpdateRestaurantCommand(
        UUID ownerId,
        String nameOfRestaurant,
        Cuisine cuisine,
        OpeningStatus openingStatus,
        Integer defaultPreparationTime,
        String contactEmail,
        String picture,
        Map<DayOfWeek, DayIntervalRequest> workingHours
) {
}
