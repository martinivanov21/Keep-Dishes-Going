package be.kdg.keepdishesgoing.customerOrder.adapter;

import be.kdg.keepdishesgoing.common.events.RestaurantCreateEvent;
import be.kdg.keepdishesgoing.common.events.RestaurantOpeningStatusChangedEvent;
import be.kdg.keepdishesgoing.customerOrder.domain.*;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveRestaurantPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;


@Component
public class RestaurantProjectionEventListener {

    private final SaveRestaurantPort saveRestaurantPort;
    Logger log = LoggerFactory.getLogger(RestaurantProjectionEventListener.class);

    public RestaurantProjectionEventListener(SaveRestaurantPort saveRestaurantPort) {
        this.saveRestaurantPort = saveRestaurantPort;
    }


    @EventListener
    @Transactional
    public void on(RestaurantCreateEvent event) {
        log.info("Received RestaurantCreateEvent: {}", event.restaurantName());
        log.info("Event ID: {}", event.restaurantId());

        var hours = event.workingHours().stream()
                .map(dto -> new WorkingHour(
                        DayOfWeek.valueOf(dto.dayOfWeek()),
                        LocalTime.parse(dto.openingTime()),
                        LocalTime.parse(dto.closingTime())
                ))
                .toList();

        Cuisine cuisine = Cuisine.valueOf(event.cuisine());
        MenuId menuId = (event.menuId() != null) ? MenuId.of(event.menuId().toString()) : null;

        Restaurant restaurant = new Restaurant(
                RestaurantId.of(event.restaurantId()),
                event.restaurantName(),
                cuisine,
                event.pictureUrl(),
                hours,
                PriceRange.REGULAR,
                BigDecimal.ZERO,
                event.defaultPreparationTime(),
                menuId,
                OpeningStatus.OPEN,
                event.deliveryStreet(),
                event.deliveryNumber(),
                event.deliveryCity()
        );

        saveRestaurantPort.saveRestaurant(restaurant);
        log.info("Restaurant Saved Successfully");
    }


    @EventListener
    @Transactional
    public void on(RestaurantOpeningStatusChangedEvent event) {
        log.info("Received RestaurantOpeningStatusChangedEvent: {}", event.restaurantId());

    }


}
