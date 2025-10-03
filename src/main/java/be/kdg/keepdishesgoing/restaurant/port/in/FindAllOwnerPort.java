package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.domain.Owner;

import java.util.List;

public interface FindAllOwnerPort {

    List<Owner> findAllOwners();
}
