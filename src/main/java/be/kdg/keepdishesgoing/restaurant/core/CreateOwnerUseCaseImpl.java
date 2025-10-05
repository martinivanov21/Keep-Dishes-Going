package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.domain.Owner;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateOwnerCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateOwnerUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.owner.SaveOwnerPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateOwnerUseCaseImpl implements CreateOwnerUseCase {

    private final SaveOwnerPort saveOwnerPort;

    public CreateOwnerUseCaseImpl(SaveOwnerPort saveOwnerPort) {
        this.saveOwnerPort = saveOwnerPort;
    }

    @Override
    @Transactional
    public Owner createOwner(CreateOwnerCommand createOwnerCommand) {
        Owner owner = createOwnerCommand.owner();
        return saveOwnerPort.save(owner);
    }
}
