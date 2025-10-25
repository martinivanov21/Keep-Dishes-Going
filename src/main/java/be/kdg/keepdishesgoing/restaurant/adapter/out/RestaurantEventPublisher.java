package be.kdg.keepdishesgoing.restaurant.adapter.out;

import be.kdg.keepdishesgoing.common.events.DomainEvent;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.domain.RestaurantId;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.DeleteRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.UpdateRestaurantPort;
import org.hibernate.annotations.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantEventPublisher implements SaveRestaurantPort {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantEventPublisher.class);
    private final ApplicationEventPublisher applicationEventPublisher;

    public RestaurantEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

//    @Override
//    public Restaurant update(Restaurant restaurant) {
//        restaurant.getUncommitedEvents().forEach(applicationEventPublisher::publishEvent);
//        return restaurant;
//    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        List<DomainEvent> events = restaurant.getUncommitedEvents();
        logger.info("Publishing {} events for restaurant: {}", events.size(), restaurant.getRestaurantId());

        restaurant.getUncommitedEvents().forEach(applicationEventPublisher::publishEvent);
        return restaurant;
    }

//    @Override
//    public void delete(RestaurantId restaurantId) {
//
//    }
}
