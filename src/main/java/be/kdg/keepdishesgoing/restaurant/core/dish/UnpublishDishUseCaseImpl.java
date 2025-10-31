package be.kdg.keepdishesgoing.restaurant.core.dish;

import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.UnpublishDishCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.UnpublishDishUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.LoadDishPort;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.SaveDishPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnpublishDishUseCaseImpl implements UnpublishDishUseCase {

    private final LoadDishPort loadDishPort;
    private final List<SaveDishPort> saveDishPorts;

    public UnpublishDishUseCaseImpl(LoadDishPort loadDishPort, List<SaveDishPort> saveDishPorts) {
        this.loadDishPort = loadDishPort;
        this.saveDishPorts = saveDishPorts;
    }

    @Override
    public Dish unpublishDish(UnpublishDishCommand unpublishDishCommand) {
        Dish dish=loadDishPort.loadById(unpublishDishCommand.dishId()).get();
        dish.unpublish();

        for (SaveDishPort saveDishPort : saveDishPorts) {
            dish = saveDishPort.saveDish(dish);
        }

        return dish;
    }
}
