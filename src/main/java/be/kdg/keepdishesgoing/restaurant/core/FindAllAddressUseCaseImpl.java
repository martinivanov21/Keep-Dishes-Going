package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.domain.Address;
import be.kdg.keepdishesgoing.restaurant.domain.AddressId;
import be.kdg.keepdishesgoing.restaurant.port.in.FindAllAddressUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.address.LoadAddressPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FindAllAddressUseCaseImpl implements FindAllAddressUseCase {

    private final LoadAddressPort loadAddressPort;

    public FindAllAddressUseCaseImpl(LoadAddressPort loadAddressPort) {
        this.loadAddressPort = loadAddressPort;
    }

    @Override
    public List<Address> findAll() {
        return loadAddressPort.loadAll();
    }

    @Override
    public Optional<Address> findById(UUID addressId) {
        return loadAddressPort.loadById(AddressId.of(addressId.toString()));
    }
}
