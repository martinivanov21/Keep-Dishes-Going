package be.kdg.keepdishesgoing.restaurant.core.dish;

import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.PublishDishCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.PublishDishUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.LoadDishPort;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.SaveDishPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishDishUseCaseImpl implements PublishDishUseCase {

    private final static Logger logger = LoggerFactory.getLogger(PublishDishUseCaseImpl.class);
    private final LoadDishPort loadDishPort;
    private final List<SaveDishPort> saveDishPorts;

    public PublishDishUseCaseImpl(LoadDishPort loadDishPort, List<SaveDishPort> saveDishPorts) {
        this.loadDishPort = loadDishPort;
        this.saveDishPorts = saveDishPorts;
    }

    @Override
    @Transactional
    public Dish publishDish(PublishDishCommand command) {
        Dish dish = loadDishPort.loadById(command.dishId())
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        logger.info("Publishing dish: {}", dish.getDishId().uuid());

        dish.publish();

        logger.info("Dish status after publish: {}", dish.getStatus());
        logger.info("Uncommitted events: {}", dish.getUncommittedEvents().size());

        for (SaveDishPort port : saveDishPorts) {
            dish = port.saveDish(dish);
        }

        return dish;

    }
}
