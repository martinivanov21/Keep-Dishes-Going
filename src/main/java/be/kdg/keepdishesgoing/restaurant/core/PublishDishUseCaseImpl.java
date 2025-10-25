package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.port.in.PublishDishCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.PublishDishUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.LoadDishPort;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.SaveDishPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishDishUseCaseImpl implements PublishDishUseCase {

    private final LoadDishPort loadDishPort;
    private final List<SaveDishPort> saveDishPorts;

    public PublishDishUseCaseImpl(LoadDishPort loadDishPort, List<SaveDishPort> saveDishPorts) {
        this.loadDishPort = loadDishPort;
        this.saveDishPorts = saveDishPorts;
    }

    @Override
    @Transactional
    public Dish publishDish(PublishDishCommand command) {
        Dish dish = loadDishPort.loadbyId(command.dishId())
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        dish.publish();

        for (SaveDishPort port : saveDishPorts) {
            dish = port.saveDish(dish);
        }
        return dish;

    }
}
