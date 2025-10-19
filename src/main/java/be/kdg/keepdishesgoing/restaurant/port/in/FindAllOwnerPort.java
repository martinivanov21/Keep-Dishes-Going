package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.domain.Owner;

import java.util.List;
import java.util.Optional;

public interface FindAllOwnerPort {

    List<Owner> findAllOwners();

    Optional<Owner> findByEmail(String email);
}
