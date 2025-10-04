package be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant;

import be.kdg.keepdishesgoing.common.events.EventCatalog;
import be.kdg.keepdishesgoing.restaurant.adapter.out.RestaurantEventJpaEntity;
import be.kdg.keepdishesgoing.restaurant.domain.OwnerId;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;

import be.kdg.keepdishesgoing.restaurant.domain.RestaurantId;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.DeleteRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.LoadRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.UpdateRestaurantPort;
import org.jmolecules.event.annotation.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantJpaAdapter implements LoadRestaurantPort, UpdateRestaurantPort, SaveRestaurantPort,
        DeleteRestaurantPort {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantJpaAdapter.class);
    private final RestaurantJpaRepository restaurants;

    public RestaurantJpaAdapter(RestaurantJpaRepository restaurants) {
        this.restaurants = restaurants;
    }




    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return null;
    }


    @Override
    public Optional<Restaurant> loadBy(OwnerId ownerId) {
        return Optional.empty();
    }

    @Override
    public List<Restaurant> loadAll() {
        return List.of();
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return null;
    }
}
