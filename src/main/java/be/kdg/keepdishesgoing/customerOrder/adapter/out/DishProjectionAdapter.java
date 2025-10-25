package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.domain.Dish;
import be.kdg.keepdishesgoing.customerOrder.domain.DishId;
import be.kdg.keepdishesgoing.customerOrder.domain.RestaurantId;
import be.kdg.keepdishesgoing.customerOrder.port.out.DeleteDishPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadDishPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveDishPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DishProjectionAdapter implements SaveDishPort, LoadDishPort, DeleteDishPort {

    private static final Logger logger = LoggerFactory.getLogger(DishProjectionAdapter.class);

    private final DishMapper dishMapper;
    private final DishProjectionJpaRepository dishRepository;

    public DishProjectionAdapter(DishMapper dishMapper, DishProjectionJpaRepository dishRepository) {
        this.dishMapper = dishMapper;
        this.dishRepository = dishRepository;
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
        return List.of();
    }

    @Override
    @Transactional
    public Dish saveDish(Dish dish) {
        logger.info("Saving dish projection: {}", dish.getNameOfDish());

        DishCustomerOrderJpaEntity entity = dishMapper.toEntity(dish);
        DishCustomerOrderJpaEntity saved = dishRepository.save(entity);

        return dishMapper.toDomain(saved);
    }
}
