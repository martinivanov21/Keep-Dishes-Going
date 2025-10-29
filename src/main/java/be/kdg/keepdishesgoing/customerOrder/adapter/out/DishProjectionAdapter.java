package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.adapter.mapper.DishMapperProjection;
import be.kdg.keepdishesgoing.customerOrder.domain.Dish;
import be.kdg.keepdishesgoing.customerOrder.domain.DishId;
import be.kdg.keepdishesgoing.customerOrder.domain.RestaurantId;
import be.kdg.keepdishesgoing.customerOrder.port.out.DeleteDishPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadDishPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveDishPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DishProjectionAdapter implements SaveDishPort, LoadDishPort, DeleteDishPort {

    private static final Logger logger = LoggerFactory.getLogger(DishProjectionAdapter.class);

    private final DishMapperProjection dishMapper;
    private final DishProjectionJpaRepository dishRepository;
    private final MenuCustomerOrderJpaRepository menuRepository;

    public DishProjectionAdapter(DishMapperProjection dishMapper, DishProjectionJpaRepository dishRepository, MenuCustomerOrderJpaRepository menuRepository) {
        this.dishMapper = dishMapper;
        this.dishRepository = dishRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    @Transactional
    public void deleteDish(DishId dishId) {
        logger.info("Deleting dish projection: {}", dishId.uuid());
        dishRepository.deleteById(dishId.uuid());
    }

    @Override
    public Optional<Dish> loadById(DishId dishId) {
        return dishRepository.findById(dishId.uuid())
                .map(dishMapper::toDomain);
    }

    @Override
    public List<Dish> loadAll() {
        return dishRepository.findAll().stream()
                .map(dishMapper::toDomain)
                .toList();
    }

    @Override
    public List<Dish> loadByRestaurantId(RestaurantId restaurantId) {
        return dishRepository.findByMenu_RestaurantId(restaurantId.uuid()).stream()
                .map(dishMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public Dish saveDish(Dish dish) {
        logger.info("Saving dish projection: {}", dish.getNameOfDish());

        if (dish.getMenuId() == null) {
            throw new IllegalStateException("Dish must have a menuId to be projected");
        }
        MenuCustomerOrderJpaEntity menuRef;
        try {
            menuRef = menuRepository.getReferenceById(dish.getMenuId().menuId());
        } catch (EntityNotFoundException e) {
            throw new IllegalStateException("Menu projection not found for id: " + dish.getMenuId().menuId(), e);
        }
        DishCustomerOrderJpaEntity entity = dishMapper.toEntity(dish);
        entity.setMenu(menuRef);

        DishCustomerOrderJpaEntity saved = dishRepository.save(entity);
        return dishMapper.toDomain(saved);
    }
}
