package be.kdg.keepdishesgoing.restaurant.port.out.address;

import be.kdg.keepdishesgoing.restaurant.domain.Address;
import be.kdg.keepdishesgoing.restaurant.domain.AddressId;

import java.util.Optional;

public interface LoadAddressPort {
    Optional<Address> loadById(AddressId addressId);
}
