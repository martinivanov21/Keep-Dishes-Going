package be.kdg.keepdishesgoing.restaurant.core;


import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateRestaurantCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateRestaurantUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateRestaurantUseCaseImpl implements CreateRestaurantUseCase {

    private final SaveRestaurantPort saveRestaurantPort;

    public CreateRestaurantUseCaseImpl(SaveRestaurantPort saveRestaurantPort) {
        this.saveRestaurantPort = saveRestaurantPort;
    }

    @Override
    @Transactional
    public Restaurant createRestaurant(CreateRestaurantCommand command) {
        return saveRestaurantPort.saveRestaurant(command.restaurant());
    }

}
