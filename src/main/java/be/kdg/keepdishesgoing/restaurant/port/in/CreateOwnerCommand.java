package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.domain.Owner;

import java.util.UUID;

public record CreateOwnerCommand(Owner owner) {
}
