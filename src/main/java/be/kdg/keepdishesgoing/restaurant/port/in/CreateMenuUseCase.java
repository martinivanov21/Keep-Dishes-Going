package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.domain.Menu;

public interface CreateMenuUseCase {
    Menu createMenu(CreateMenuCommand command);
}
