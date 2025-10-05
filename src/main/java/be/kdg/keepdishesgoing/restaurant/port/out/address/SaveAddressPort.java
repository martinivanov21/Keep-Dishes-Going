package be.kdg.keepdishesgoing.restaurant.port.out.address;

import be.kdg.keepdishesgoing.restaurant.domain.Address;

public interface SaveAddressPort {
    Address save(Address address);
}
