package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.common.domain.Address;
import be.kdg.keepdishesgoing.common.domain.Person;
import be.kdg.keepdishesgoing.common.domain.Role;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.domain.ScheduleHour;
import be.kdg.keepdishesgoing.restaurant.domain.enums.Cuisine;
import be.kdg.keepdishesgoing.restaurant.domain.enums.OpeningStatus;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateRestaurantCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateRestaurantUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.LoadOwnerPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.LoadRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateRestaurantUseCaseImpl implements CreateRestaurantUseCase {

    private final LoadOwnerPort loadOwnerPort;
    private final SaveRestaurantPort saveRestaurantPort;
    private final LoadRestaurantPort loadRestaurantPort;

    public CreateRestaurantUseCaseImpl(LoadOwnerPort loadOwnerPort,
                                       SaveRestaurantPort saveRestaurantPort, LoadRestaurantPort loadRestaurantPort) {
        this.loadOwnerPort = loadOwnerPort;
        this.saveRestaurantPort = saveRestaurantPort;
        this.loadRestaurantPort = loadRestaurantPort;
    }

    @Override
    @Transactional
    public Restaurant createRestaurant(CreateRestaurantCommand command) {
        Person owner = loadOwnerPort.loadOwnerById(command.ownerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        if (owner.getRole() != Role.MANAGER) {
            throw new IllegalArgumentException("Only MANAGER can own a restaurant");
        }

        Address address = new Address(
                command.street(),
                command.number(),
                command.postalCode(),
                command.city(),
                command.country());

        ScheduleHour opening = ScheduleHour.from(command.openingHours());
        ScheduleHour closing = ScheduleHour.from(command.closingHours());
        Cuisine cuisine = Cuisine.valueOf(command.cuisine().toUpperCase());

        Restaurant restaurant = new Restaurant();
        restaurant.setNameOfRestaurant(command.name());
        restaurant.setAddress(address);
        restaurant.setPicture(command.picture());
        restaurant.setOwner(owner);
        restaurant.setOpeningHours(opening);
        restaurant.setClosingHours(closing);
        restaurant.setCuisine(cuisine);
        restaurant.setOpeningStatus(OpeningStatus.CLOSED);

        return saveRestaurantPort.saveRestaurant(restaurant);

    }
}
