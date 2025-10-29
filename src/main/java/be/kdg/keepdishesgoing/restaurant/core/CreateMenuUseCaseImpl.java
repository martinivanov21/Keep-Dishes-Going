package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.adapter.out.MenuEventPublisher;
import be.kdg.keepdishesgoing.restaurant.domain.Menu;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateMenuCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateMenuUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.menu.SaveMenuPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateMenuUseCaseImpl implements CreateMenuUseCase {

    private final List<SaveMenuPort> saveMenuPorts;
    private final MenuEventPublisher menuEventPublisher;

    public CreateMenuUseCaseImpl(List<SaveMenuPort> saveMenuPorts, MenuEventPublisher menuEventPublisher) {
        this.saveMenuPorts = saveMenuPorts;
        this.menuEventPublisher = menuEventPublisher;
    }


    @Override
    @Transactional
    public Menu createMenu(CreateMenuCommand command) {
        Menu menu = command.menu();

        menu.create();

        for (SaveMenuPort saveMenuPort : saveMenuPorts) {
            menu = saveMenuPort.save(menu);
        }
        return menu;
    }
}
