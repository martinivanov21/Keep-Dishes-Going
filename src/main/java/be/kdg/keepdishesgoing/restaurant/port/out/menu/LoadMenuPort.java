package be.kdg.keepdishesgoing.restaurant.port.out.menu;

import be.kdg.keepdishesgoing.restaurant.domain.Menu;
import be.kdg.keepdishesgoing.restaurant.domain.MenuId;

import java.util.List;
import java.util.Optional;

public interface LoadMenuPort {

    Optional<Menu> loadById(MenuId menuId);
    List<Menu> loadAll();
}
