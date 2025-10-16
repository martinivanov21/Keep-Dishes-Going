package be.kdg.keepdishesgoing.restaurant.adapter.in.response;


import java.util.List;
import java.util.UUID;

public record RestaurantDto(UUID uuid,
                            String nameOfRestaurant,
                            String cuisine,
                            int defaultPreparationTime,
                            String contactEmail,
                            String picture,
                            String openingStatus,
                            UUID addressId,
                            List<ScheduleHourDto> workingHours,
                            UUID ownerId,
                            MenuDto menu) {
}
