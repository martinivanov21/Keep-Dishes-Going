package be.kdg.keepdishesgoing.restaurant.adapter.out.menu;

import be.kdg.keepdishesgoing.restaurant.adapter.out.dish.DishJpaEntity;
import be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant.RestaurantJpaEntity;
import be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant.RestaurantJpaRepository;
import be.kdg.keepdishesgoing.restaurant.domain.DishId;
import be.kdg.keepdishesgoing.restaurant.domain.Menu;
import be.kdg.keepdishesgoing.restaurant.domain.MenuId;
import be.kdg.keepdishesgoing.restaurant.domain.RestaurantId;
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
    private final RestaurantJpaRepository restaurantJpaRepository;

    public MenuJpaAdapter(MenuJpaRepository menuJpaRepository, RestaurantJpaRepository restaurantJpaRepository) {
        this.menuJpaRepository = menuJpaRepository;
        this.restaurantJpaRepository = restaurantJpaRepository;
    }

    @Override
    public Optional<Menu> loadById(MenuId menuId) {
        return menuJpaRepository.findByIdWithRestaurant(menuId.uuid())
                .map(entity -> {
                    RestaurantId restaurantId = null;
                    if (entity.getRestaurant() != null) {
                        restaurantId = new RestaurantId(entity.getRestaurant().getRestaurantId());
                    }

                    return new Menu(
                            new MenuId(entity.getMenuId()),
                            restaurantId,
                            List.of()
                    );
                });
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
        MenuJpaEntity menuEntity = new MenuJpaEntity();
        menuEntity.setMenuId(menu.getMenuId().uuid());

        MenuJpaEntity savedMenu = menuJpaRepository.save(menuEntity);

        if (menu.getRestaurantId() != null) {
            RestaurantJpaEntity restaurant = restaurantJpaRepository
                    .findById(menu.getRestaurantId().uuid())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Restaurant not found: " + menu.getRestaurantId().uuid()));

            restaurant.setMenu(savedMenu);
            restaurantJpaRepository.save(restaurant);
        }

        return new Menu(
                new MenuId(savedMenu.getMenuId()),
                menu.getRestaurantId(),
                List.of()
        );
    }

    private MenuJpaEntity mapToEntity(Menu menu) {
        MenuJpaEntity entity = new MenuJpaEntity();
        entity.setMenuId(menu.getMenuId().uuid());
        if (menu.getRestaurantId() != null) {
            RestaurantJpaEntity restaurant = restaurantJpaRepository
                    .findById(menu.getRestaurantId().uuid())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Restaurant not found: " + menu.getRestaurantId().uuid()));

            entity.setRestaurant(restaurant);
        }

        return entity;
    }


    private Menu mapToDomain(MenuJpaEntity entity) {
        RestaurantId restaurantId = null;
        if (entity.getRestaurant() != null) {
            restaurantId = new RestaurantId(entity.getRestaurant().getRestaurantId());
        } else {
            logger.info("Menu {} has no restaurant!", entity.getMenuId());
        }
        return new Menu(
                new MenuId(entity.getMenuId()),restaurantId, List.of());

    }

}
