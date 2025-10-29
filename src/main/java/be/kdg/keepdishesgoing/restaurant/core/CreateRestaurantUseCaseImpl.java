package be.kdg.keepdishesgoing.restaurant.core;


import be.kdg.keepdishesgoing.common.events.DomainEvent;
import be.kdg.keepdishesgoing.restaurant.domain.Address;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateRestaurantCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateRestaurantUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.address.LoadAddressPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateRestaurantUseCaseImpl implements CreateRestaurantUseCase {

    private static final Logger logger =  LoggerFactory.getLogger(CreateRestaurantUseCaseImpl.class);
    private final List<SaveRestaurantPort> saveRestaurantPorts;
    private final LoadAddressPort loadAddressPort;

    public CreateRestaurantUseCaseImpl(List<SaveRestaurantPort> saveRestaurantPorts, LoadAddressPort loadAddressPort) {
        this.saveRestaurantPorts = saveRestaurantPorts;
        this.loadAddressPort = loadAddressPort;
    }


    @Override
    @Transactional
    public Restaurant createRestaurant(CreateRestaurantCommand command) {
        Restaurant restaurant = command.restaurant();

        Address address = null;
        if (restaurant.getAddressId() != null) {
            address = loadAddressPort.loadById(restaurant.getAddressId()).orElse(null);
        }

        restaurant.raiseRestaurantCreatedEvent(
                address != null ? address.getStreet()  : "",
                address != null ? address.getNumber()  : 0,
                address != null ? address.getCity()    : ""
        );

        logger.info("Creating restaurant with {} uncommitted events",
                restaurant.getUncommitedEvents().size());

        for (SaveRestaurantPort port : saveRestaurantPorts) {
            logger.info("Calling SaveRestaurantPort: {}", port.getClass().getSimpleName());
            restaurant = port.saveRestaurant(restaurant);
        }
        return restaurant;
    }

}
