package be.kdg.keepdishesgoing.restaurant.adapter.in.request;

public record CreateAddressRequest(
        String street,
        int number,
        String postalCode,
        String city,
        String country
) {
}
