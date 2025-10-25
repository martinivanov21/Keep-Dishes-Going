package be.kdg.keepdishesgoing.customerOrder.adapter;

import be.kdg.keepdishesgoing.common.events.RestaurantCreateEvent;
import be.kdg.keepdishesgoing.common.events.RestaurantOpeningStatusChangedEvent;
import be.kdg.keepdishesgoing.customerOrder.domain.*;
//import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveRestaurantPort;
import be.kdg.keepdishesgoing.customerOrder.domain.ScheduleHourId;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;


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
        log.info("========================================");
        log.info("Received RestaurantCreateEvent: {}", event.restaurantName());
        log.info("Event ID: {}", event.restaurantId());
        log.info("========================================");


        Restaurant restaurant = new Restaurant(
                RestaurantId.of(event.restaurantId()),
                event.restaurantName(),
                null,
                Cuisine.valueOf(event.cuisine()),
                event.pictureUrl(),
                event.workingHours().stream()
                        .map(dto -> new ScheduleHour(
                                ScheduleHourId.of(dto.scheduleHourId().toString()),
                                dto.dayOfWeek(),
                                dto.openingTime(),
                                dto.closingTime()
                        )).toList(),
                PriceRange.REGULAR,
                BigDecimal.ZERO,
                event.defaultPreparationTime(),
                new ArrayList<>()
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
