package be.kdg.keepdishesgoing.restaurant.port.out.menu;

import be.kdg.keepdishesgoing.restaurant.domain.Menu;

public interface SaveMenuPort {
    Menu save(Menu menu);
}
