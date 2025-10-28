package be.kdg.keepdishesgoing.customerOrder.port.out;

import be.kdg.keepdishesgoing.customerOrder.domain.Menu;
import be.kdg.keepdishesgoing.customerOrder.domain.MenuId;

import java.util.List;

public interface LoadMenuPort {
    Menu loadById(MenuId menuId);
    List<Menu> loadAll();
}
