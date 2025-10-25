package be.kdg.keepdishesgoing.restaurant.adapter.out;

import be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant.RestaurantJpaEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "restaurant_events", schema = "kdg_restaurant")
public class RestaurantEventJpaEntity {

    @Id
    private UUID uuid;
    @Column(nullable = false)
    private  String eventPit;
    @Column(nullable = false, length = 50)
    private String eventType;
    @Column(nullable = false)
    private UUID owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_uuid", updatable = false)
    private RestaurantJpaEntity restaurant;

    public RestaurantEventJpaEntity() {
    }

    public RestaurantEventJpaEntity(RestaurantJpaEntity restaurant, UUID owner,
                                    String eventType, String eventPit, UUID uuid) {
        this.restaurant = restaurant;
        this.owner = owner;
        this.eventType = eventType;
        this.eventPit = eventPit;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEventPit() {
        return eventPit;
    }

    public void setEventPit(String eventPit) {
        this.eventPit = eventPit;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public RestaurantJpaEntity getRestaurant() {
        return restaurant;
    }

//    public void setRestaurant(RestaurantJpaEntity restaurant) {
//        this.restaurant = restaurant;
//        if (restaurant != null) {
//            this.restaurantUuid = restaurant.getRestaurantId();
//        }
//    }
//
//    public UUID getRestaurantUuid() {
//        return restaurantUuid;
//    }
//
//    public void setRestaurantUuid(UUID restaurantUuid) {
//        this.restaurantUuid = restaurantUuid;
//    }


}
