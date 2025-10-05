package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.domain.Address;

public interface CreateAddressUseCase {
    Address createAddress(CreateAddressCommand createAddressCommand);
}
