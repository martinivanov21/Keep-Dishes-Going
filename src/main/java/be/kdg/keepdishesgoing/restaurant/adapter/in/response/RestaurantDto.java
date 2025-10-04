package be.kdg.keepdishesgoing.restaurant.adapter.in.response;

import java.util.UUID;

public record RestaurantDto(UUID uuid, String nameOfRestaurant, String contactEmail, String picture,
                            String cuisine, String openingStatus) {
}
