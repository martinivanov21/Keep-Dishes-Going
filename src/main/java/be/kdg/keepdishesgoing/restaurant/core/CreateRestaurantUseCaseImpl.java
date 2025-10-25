package be.kdg.keepdishesgoing.restaurant.core;


import be.kdg.keepdishesgoing.common.events.DomainEvent;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateRestaurantCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateRestaurantUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateRestaurantUseCaseImpl implements CreateRestaurantUseCase {

    private final List<SaveRestaurantPort> saveRestaurantPorts;

    public CreateRestaurantUseCaseImpl(List<SaveRestaurantPort> saveRestaurantPorts) {
        this.saveRestaurantPorts = saveRestaurantPorts;
    }


    @Override
    @Transactional
    public Restaurant createRestaurant(CreateRestaurantCommand command) {
        Restaurant restaurant = command.restaurant();

        for (SaveRestaurantPort port : saveRestaurantPorts) {
            restaurant = port.saveRestaurant(restaurant) ;
        }
        return restaurant;
    }

}
