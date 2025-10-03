package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.domain.Owner;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateOwnerCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateOwnerUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateOwnerUseCaseImpl implements CreateOwnerUseCase {
    @Override
    public Owner createOwner(CreateOwnerCommand createOwnerCommand) {
        return null;
    }
}
