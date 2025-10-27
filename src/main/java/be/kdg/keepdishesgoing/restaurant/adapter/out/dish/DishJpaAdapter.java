package be.kdg.keepdishesgoing.restaurant.adapter.out.dish;

import be.kdg.keepdishesgoing.restaurant.adapter.out.menu.MenuJpaEntity;
import be.kdg.keepdishesgoing.restaurant.domain.*;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.DeleteDishPort;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.LoadDishPort;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.SaveDishPort;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.UpdateDishPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DishJpaAdapter implements UpdateDishPort, LoadDishPort, DeleteDishPort, SaveDishPort {

    private static final Logger logger = LoggerFactory.getLogger(DishJpaAdapter.class);
    private final DishJpaRepository dishJpaRepository;

    public DishJpaAdapter(DishJpaRepository dishJpaRepository) {
        this.dishJpaRepository = dishJpaRepository;
    }

    @Override
    @Transactional
    public Dish saveDish(Dish dish) {
        DishJpaEntity dishJpaEntity = mapToEntity(dish);
        DishJpaEntity saved = dishJpaRepository.save(dishJpaEntity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Dish> loadById(DishId dishId) {
        return dishJpaRepository.findById(dishId.uuid())
                .map(this::mapToDomain);
    }

    @Override
    public List<Dish> findByRestaurantId(RestaurantId restaurantId) {
        logger.info("Finding dishes by restaurant id: {}", restaurantId.uuid());

        return dishJpaRepository.findDishesByRestaurantId(restaurantId.uuid()).stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    @Transactional
    public Dish updateDish(Dish dish) {
        logger.debug("Updating Dish {}", dish.getDishId());

        return saveDish(dish);

    }
    @Override
    @Transactional
    public void deleteDish(DishId dishId) {
        logger.info("Deleting dish: {}", dishId.uuid());
        dishJpaRepository.deleteById(dishId.uuid());
    }




    private DishJpaEntity mapToEntity(Dish dish) {
        DishVersionJpaEntity liveVersionEntity = mapToEntity(dish.getLiveVersion());
        DishVersionJpaEntity draftVersionEntity = mapToEntity(dish.getDraftVersion());
        MenuJpaEntity menuEntity = mapMenuToEntity(dish.getMenu());

        return new DishJpaEntity(
                dish.getDishId().uuid(),
                liveVersionEntity,
                draftVersionEntity,
                dish.getStatus(),
                dish.getQuantity(),
                menuEntity
        );
    }

    private Dish mapToDomain(DishJpaEntity entity) {
        DishVersion liveVersion = mapToDomain(entity.getLiveVersion());
        DishVersion draftVersion = mapToDomain(entity.getDraftVersion());
        Menu menu = mapMenuToDomain(entity.getMenu());

        return new Dish(
                new DishId(entity.getDishId()),
                liveVersion,
                draftVersion,
                entity.getStatus(),
                entity.getQuantity(),
                menu
        );
    }

    private DishVersionJpaEntity mapToEntity(DishVersion version) {
        if (version == null) return null;
        return new DishVersionJpaEntity(
                version.getDishVersionId().uuid(),
                version.getNameOfDish(),
                version.getDescription(),
                version.getPrice(),
                version.getPicture(),
                version.getPreparationTime(),
                version.getFoodTag(),
                version.getTypeOfDish()
        );
    }

    private DishVersion mapToDomain(DishVersionJpaEntity entity) {
        if (entity == null) return null;
        return new DishVersion(
                new DishVersionId(entity.getDishVersionId()),
                entity.getNameOfDish(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getPicture(),
                entity.getPreparationTime(),
                entity.getFoodTag(),
                entity.getDishType()
        );
    }

    private MenuJpaEntity mapMenuToEntity(Menu menu) {
        if (menu == null) return null;

        MenuJpaEntity entity = new MenuJpaEntity();
        entity.setMenuId(menu.getMenuId().uuid());
        return entity;
    }

    private Menu mapMenuToDomain(MenuJpaEntity entity) {
        if (entity == null) return null;

        RestaurantId restaurantId = entity.getRestaurant() != null
                ? new RestaurantId(entity.getRestaurant().getRestaurantId())
                : null;

        return new Menu(
                new MenuId(entity.getMenuId()),
                restaurantId,
                List.of()
        );
    }



}
