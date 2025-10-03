package be.kdg.keepdishesgoing.restaurant.adapter.in.response;


import be.kdg.keepdishesgoing.restaurant.domain.OwnerId;

public record CreateRestaurantRequest(
        String name, String contactEmail, String picture,
        String street, String city, String postalCode, int number, String country, int defaultPreparationTime,
        String cuisine, String openingHours, String closingHours, OwnerId ownerId
) {
}
