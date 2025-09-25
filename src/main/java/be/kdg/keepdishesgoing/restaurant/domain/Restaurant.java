package be.kdg.keepdishesgoing.restaurant.domain;

import be.kdg.keepdishesgoing.common.domain.Address;
import be.kdg.keepdishesgoing.common.domain.Person;
import be.kdg.keepdishesgoing.restaurant.domain.enums.Cusine;
import be.kdg.keepdishesgoing.restaurant.domain.enums.OpeningStatus;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Restaurant {

    private UUID restaurantId;
    private String nameOfRestaurant;
    private ScheduleHour openingHours;
    private ScheduleHour closingHours;
    private Cusine cusine;
    private OpeningStatus openingStatus;
    private String contactEmail;
    private String picture;
    private Address address;
    private Person owner;
    private List<Dish> dishes;

    private final List<Object> domainEvents = new ArrayList<>();

    public double getAveragePrice() {
        return dishes.stream()
                .map(Dish::getCurrentPrice)
                .flatMap(Optional::stream)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

}
