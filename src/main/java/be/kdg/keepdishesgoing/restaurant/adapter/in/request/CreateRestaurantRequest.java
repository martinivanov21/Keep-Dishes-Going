package be.kdg.keepdishesgoing.restaurant.adapter.in.request;


import be.kdg.keepdishesgoing.restaurant.domain.*;
import be.kdg.keepdishesgoing.restaurant.domain.enums.Cuisine;
import be.kdg.keepdishesgoing.restaurant.domain.enums.OpeningStatus;

import java.util.List;

public record CreateRestaurantRequest(
        RestaurantId restaurantId,
        String nameOfRestaurant,
        Cuisine cuisine,
        OpeningStatus openingStatus,
        int defaultPreparationTime,
        String contactEmail,
        String picture,
        String addressId,
        String ownerId
//        Menu menu,
//        List<ScheduleHour>workingHours
) {
}
