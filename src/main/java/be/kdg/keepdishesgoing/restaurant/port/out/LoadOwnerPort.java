package be.kdg.keepdishesgoing.restaurant.port.out;

import be.kdg.keepdishesgoing.common.domain.Person;
import be.kdg.keepdishesgoing.common.domain.PersonId;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;

import java.util.Optional;

public interface LoadOwnerPort {
    Optional<Person> loadOwnerById(PersonId personId);
}
