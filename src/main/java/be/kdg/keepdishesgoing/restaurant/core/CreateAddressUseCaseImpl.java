package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.domain.Address;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateAddressCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateAddressUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.address.SaveAddressPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateAddressUseCaseImpl implements CreateAddressUseCase {

    private final SaveAddressPort saveAddressPort;

    public CreateAddressUseCaseImpl(SaveAddressPort saveAddressPort) {
        this.saveAddressPort = saveAddressPort;
    }

    @Override
    @Transactional
    public Address createAddress(CreateAddressCommand createAddressCommand) {
        Address address = createAddressCommand.address();
        return saveAddressPort.save(address);
    }
}
