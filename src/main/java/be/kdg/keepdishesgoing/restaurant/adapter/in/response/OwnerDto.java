package be.kdg.keepdishesgoing.restaurant.adapter.in.response;


import java.util.UUID;

public record OwnerDto(UUID ownerId,String firstName, String lastName, String email) {
}
