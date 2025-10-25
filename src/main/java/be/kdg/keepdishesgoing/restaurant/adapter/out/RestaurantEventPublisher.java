package be.kdg.keepdishesgoing.restaurant.adapter.out;

import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.domain.RestaurantId;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.DeleteRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.UpdateRestaurantPort;
import org.hibernate.annotations.Comment;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class RestaurantEventPublisher implements SaveRestaurantPort {

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
        restaurant.getUncommitedEvents().forEach(applicationEventPublisher::publishEvent);
        return restaurant;
    }

//    @Override
//    public void delete(RestaurantId restaurantId) {
//
//    }
}
