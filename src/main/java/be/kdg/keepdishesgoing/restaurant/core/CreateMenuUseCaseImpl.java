package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.domain.Menu;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateMenuCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateMenuUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.menu.SaveMenuPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateMenuUseCaseImpl implements CreateMenuUseCase {

    private final SaveMenuPort saveMenuPort;

    public CreateMenuUseCaseImpl(SaveMenuPort saveMenuPort) {
        this.saveMenuPort = saveMenuPort;
    }

    @Override
    @Transactional
    public Menu createMenu(CreateMenuCommand command) {
        return saveMenuPort.save(command.menu());
    }
}
