package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.domain.Owner;

public interface CreateOwnerUseCase {
    Owner createOwner(CreateOwnerCommand createOwnerCommand);
}
