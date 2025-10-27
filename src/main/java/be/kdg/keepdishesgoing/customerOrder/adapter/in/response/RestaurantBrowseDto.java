package be.kdg.keepdishesgoing.customerOrder.adapter.in.response;

import java.util.List;
import java.util.UUID;

public record RestaurantBrowseDto(
        UUID restaurantId,
        String name,
        String cuisine,
        String pictureUrl,
        String priceRange,
        double averagePrice,
        int estimatedDeliveryMinutes,
        List<ScheduleHourDto> workingHours
) {
}
