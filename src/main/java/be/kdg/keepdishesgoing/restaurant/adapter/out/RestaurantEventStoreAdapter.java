package be.kdg.keepdishesgoing.restaurant.adapter.out;

import be.kdg.keepdishesgoing.common.events.RestaurantCreateEvent;
import be.kdg.keepdishesgoing.restaurant.adapter.RestaurantEventJpaRepository;
import be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant.RestaurantJpaEntity;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RestaurantEventStoreAdapter implements SaveRestaurantPort {

    private final RestaurantEventJpaRepository eventRepository;
    private final ObjectMapper objectMapper;

    public RestaurantEventStoreAdapter(RestaurantEventJpaRepository eventRepository, ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    @Transactional
    public Restaurant saveRestaurant(Restaurant restaurant) {
       restaurant.getUncommitedEvents().forEach(event -> {
           try {
               RestaurantEventJpaEntity eventJpaEntity = new RestaurantEventJpaEntity();
               eventJpaEntity.setUuid(UUID.randomUUID());
               eventJpaEntity.setEventPit(event.eventPit().toString());
               eventJpaEntity.setEventType(event.getClass().getSimpleName());


               if (event instanceof RestaurantCreateEvent) {
                   eventJpaEntity.setOwner(restaurant.getOwnerId().uuid());
               }


               eventRepository.save(eventJpaEntity);
           }
           catch (Exception e) {
               throw new RuntimeException(e);
           }
       });
        return restaurant;
    }
}
