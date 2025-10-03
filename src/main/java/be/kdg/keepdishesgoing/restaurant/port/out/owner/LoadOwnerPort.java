package be.kdg.keepdishesgoing.restaurant.port.out.owner;

import be.kdg.keepdishesgoing.restaurant.domain.Owner;
import be.kdg.keepdishesgoing.restaurant.domain.OwnerId;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;

import java.util.Optional;

public interface LoadOwnerPort {
    Optional<Owner> loadOwnerById(OwnerId ownerId);
}
