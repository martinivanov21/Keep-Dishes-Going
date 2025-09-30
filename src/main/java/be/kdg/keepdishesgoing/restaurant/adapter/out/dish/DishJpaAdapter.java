package be.kdg.keepdishesgoing.restaurant.adapter.out.dish;

import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.domain.DishId;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.DeleteDishPort;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.LoadDishPort;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.UpdateDishPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DishJpaAdapter implements UpdateDishPort, LoadDishPort, DeleteDishPort {
    @Override
    public Optional<Dish> loadDish(DishId dishId) {
        return Optional.empty();
    }

    @Override
    public void updateDish(DishId dishId) {

    }
}
