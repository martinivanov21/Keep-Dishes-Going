package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.domain.Address;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateAddressCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateAddressUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.address.LoadAddressPort;
import be.kdg.keepdishesgoing.restaurant.port.out.address.SaveAddressPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateAddressUseCaseImpl implements CreateAddressUseCase {

    private final SaveAddressPort saveAddressPort;
    private final LoadAddressPort loadAddressPort;

    public CreateAddressUseCaseImpl(SaveAddressPort saveAddressPort, LoadAddressPort loadAddressPort) {
        this.saveAddressPort = saveAddressPort;
        this.loadAddressPort = loadAddressPort;
    }

    @Override
    @Transactional
    public Address createAddress(CreateAddressCommand createAddressCommand) {
        Address address = createAddressCommand.address();
        return saveAddressPort.save(address);
    }

}
