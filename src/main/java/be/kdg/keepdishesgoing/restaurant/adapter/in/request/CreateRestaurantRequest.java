package be.kdg.keepdishesgoing.restaurant.adapter.in.request;


import be.kdg.keepdishesgoing.restaurant.domain.*;
import be.kdg.keepdishesgoing.restaurant.domain.enums.Cuisine;
import be.kdg.keepdishesgoing.restaurant.domain.enums.OpeningStatus;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public record CreateRestaurantRequest(
        RestaurantId restaurantId,
        String nameOfRestaurant,
        Cuisine cuisine,
        OpeningStatus openingStatus,
        int defaultPreparationTime,
        String contactEmail,
        String picture,
        String addressId,
        String ownerId,
        MenuId menuId,
        Map<DayOfWeek,DayIntervalRequest> workingHours
//        List<ScheduleHour> workingHours
) {
}
