package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateDishVersionCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateDishVersionUseCase;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateRestaurantCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateRestaurantUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.CreateDishVersionPort;
import org.springframework.stereotype.Service;

@Service
public class CreateDishVersionUseCaseImpl implements CreateDishVersionUseCase {
   private final CreateDishVersionPort createDishVersionPort;

    public CreateDishVersionUseCaseImpl(CreateDishVersionPort createDishVersionPort) {
        this.createDishVersionPort = createDishVersionPort;
    }

    @Override
    public DishVersion create(CreateDishVersionCommand command) {
        return createDishVersionPort.save(command.dishVersion());
    }
}
