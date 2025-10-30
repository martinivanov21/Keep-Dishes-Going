package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.domain.OwnerId;

public interface CurrentOwnerPort {
    OwnerId currentOwnerId();
}
