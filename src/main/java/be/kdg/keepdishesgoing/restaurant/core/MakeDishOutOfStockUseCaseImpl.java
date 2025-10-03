package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.domain.DishId;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.domain.RestaurantId;
import be.kdg.keepdishesgoing.restaurant.domain.exceptions.UnauthorizedOwnerException;
import be.kdg.keepdishesgoing.restaurant.port.in.MakeDishOutOfStockCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.MakeDishOutOfStockUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.LoadDishPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.LoadRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.UpdateDishPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakeDIshOutOfStockUseCaseImpl implements MakeDishOutOfStockUseCase {

    private final Logger logger = LoggerFactory.getLogger(MakeDIshOutOfStockUseCaseImpl.class);
    private final LoadRestaurantPort loadRestaurantPort;
    private final LoadDishPort loadDishPort;
    private final List<UpdateDishPort> updateDishPorts;

    public MakeDIshOutOfStockUseCaseImpl(LoadRestaurantPort loadRestaurantPort, LoadDishPort loadDishPort,
                                         List<UpdateDishPort> updateDishPorts) {
        this.loadRestaurantPort = loadRestaurantPort;
        this.loadDishPort = loadDishPort;
        this.updateDishPorts = updateDishPorts;
    }

    @Override
    @Transactional
    public void makeDishOutOfStock(MakeDishOutOfStockCommand command) throws UnauthorizedOwnerException {
        Restaurant restaurant = loadRestaurantPort.loadBy(new RestaurantId(command.restaurantUUID()))
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        if (!restaurant.getOwner().getPersonId().equals(command.ownerUUID())) {
            throw new UnauthorizedOwnerException("Only the restaurant owner can make dish out of stock");
        }

        Dish dish = loadDishPort.loadDish(new DishId(command.dishId()))
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        if (!restaurant.getDishes().contains(dish)) {
            throw new IllegalStateException("Dish does not belong to this restaurant");
        }

        dish.markOutOfStock();
        logger.info("Dish {} has been marked out of stock", dish.getDishId());

        updateDishPorts.forEach(port -> port.updateDish(dish.getDishId()));

    }
}
