package be.kdg.keepdishesgoing.restaurant.adapter.out.menu;

import be.kdg.keepdishesgoing.restaurant.adapter.out.dish.DishJpaEntity;
import be.kdg.keepdishesgoing.restaurant.domain.DishId;
import be.kdg.keepdishesgoing.restaurant.domain.Menu;
import be.kdg.keepdishesgoing.restaurant.domain.MenuId;
import be.kdg.keepdishesgoing.restaurant.port.out.menu.LoadMenuPort;
import be.kdg.keepdishesgoing.restaurant.port.out.menu.SaveMenuPort;
import be.kdg.keepdishesgoing.restaurant.port.out.menu.UpdateMenuPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class MenuJpaAdapter implements LoadMenuPort, UpdateMenuPort, SaveMenuPort {

    private final Logger logger = LoggerFactory.getLogger(MenuJpaAdapter.class);
    private final MenuJpaRepository menuJpaRepository;

    public MenuJpaAdapter(MenuJpaRepository menuJpaRepository) {
        this.menuJpaRepository = menuJpaRepository;
    }

    @Override
    public Optional<Menu> loadBy(MenuId menuId) {
        return menuJpaRepository.findById(menuId.uuid())
                .map(this::mapToDomain);
    }

    @Override
    public List<Menu> loadAll() {
        return menuJpaRepository.findAll().stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Menu update(Menu menu) {
        MenuJpaEntity entity = mapToEntity(menu);
        MenuJpaEntity savedMenu = menuJpaRepository.save(entity);
        return mapToDomain(savedMenu);
    }

    @Override
    @Transactional
    public Menu save(Menu menu) {
        logger.debug("Saving menu {}", menu);
        MenuJpaEntity savedMenu = menuJpaRepository.save(mapToEntity(menu));
        return mapToDomain(savedMenu);
    }

    private MenuJpaEntity mapToEntity(Menu menu) {
        List<DishJpaEntity> dishes = menu.getDishIds().stream()
                .map(dishId -> {
                    DishJpaEntity dish = new DishJpaEntity();
                    dish.setDishId(dishId.uuid());
                    dish.setMenu(new MenuJpaEntity(menu.getMenuId().uuid(),null));
                    return dish;
                })
                .collect(Collectors.toList());

        MenuJpaEntity menuJpaEntity = new MenuJpaEntity(menu.getMenuId().uuid(),dishes);
        dishes.forEach(d -> d.setMenu(menuJpaEntity));
        return menuJpaEntity;
    }

    private Menu mapToDomain(MenuJpaEntity entity) {
        List<DishId> dishIds = entity.getDishes().stream()
                .map(dishEntity -> new DishId(dishEntity.getDishId()))
                .collect(Collectors.toList());

        return new Menu(new MenuId(entity.getMenuId()),dishIds);

    }

}
