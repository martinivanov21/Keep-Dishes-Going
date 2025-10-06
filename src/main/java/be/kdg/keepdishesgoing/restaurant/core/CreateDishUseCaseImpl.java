package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateDishCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateDishUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.SaveDishPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateDishUseCaseImpl implements CreateDishUseCase {

    private final SaveDishPort saveDishPort;

    public CreateDishUseCaseImpl(SaveDishPort saveDishPort) {
        this.saveDishPort = saveDishPort;
    }

    @Override
    @Transactional
    public Dish createDish(CreateDishCommand command) {
        return saveDishPort.saveDish(command.dish());
    }
}
