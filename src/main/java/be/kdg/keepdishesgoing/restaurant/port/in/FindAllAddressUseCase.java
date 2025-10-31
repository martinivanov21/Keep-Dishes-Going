package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.domain.Address;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindAllAddressUseCase {
    List<Address> findAll();
    Optional<Address> findById(UUID addressId);
}
