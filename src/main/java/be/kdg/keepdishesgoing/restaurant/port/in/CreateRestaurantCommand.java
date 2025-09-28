package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.common.domain.PersonId;

public record CreateRestaurantCommand(String name, String contactEmail, String picture,
                                      String street, String city, String postalCode, int number,
                                      String country, int defaultPreparationTime,
                                      String cuisine, String openingHours, String closingHours, PersonId ownerId) {
}
