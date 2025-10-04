package be.kdg.keepdishesgoing.restaurant.port.out.owner;

import be.kdg.keepdishesgoing.restaurant.domain.Owner;

public interface SaveOwnerPort {
    Owner save(Owner owner);
}
