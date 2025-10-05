package be.kdg.keepdishesgoing.restaurant.adapter.in.response;

import java.util.UUID;

public record AddressDto(UUID addressId,
                         String street,
                         int number,
                         String postalCode,
                         String city,
                         String country) {
}
