package be.kdg.keepdishesgoing.customerOrder.port.out;

import be.kdg.keepdishesgoing.customerOrder.domain.Menu;

public interface SaveMenuPort {
    Menu save(Menu menu);
}
