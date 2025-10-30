package be.kdg.keepdishesgoing.restaurant.adapter.in.request;


import be.kdg.keepdishesgoing.restaurant.domain.enums.Cuisine;
import be.kdg.keepdishesgoing.restaurant.domain.enums.OpeningStatus;

import java.time.DayOfWeek;
import java.util.Map;

public record UpdateRestaurantRequest(
        String nameOfRestaurant,
        Cuisine cuisine,
        OpeningStatus openingStatus,
        Integer defaultPreparationTime,
        String contactEmail,
        String picture,
        Map<DayOfWeek, DayIntervalRequest> workingHours
) {
}
