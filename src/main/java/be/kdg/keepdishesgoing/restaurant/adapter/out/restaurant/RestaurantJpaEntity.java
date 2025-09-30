package be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant;

import be.kdg.keepdishesgoing.common.adapter.PersonJpaEntity;
import be.kdg.keepdishesgoing.common.domain.Address;
import be.kdg.keepdishesgoing.restaurant.adapter.out.dish.DishJpaEntity;
import be.kdg.keepdishesgoing.restaurant.domain.Menu;
import be.kdg.keepdishesgoing.restaurant.domain.ScheduleHour;
import be.kdg.keepdishesgoing.restaurant.domain.enums.Cuisine;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurant")
public class RestaurantJpaEntity {

    @Id
    @Column(name = "restaurant_id",nullable = false)
    private UUID restaurantId;

    @Column(nullable = false)
    private String nameOfRestaurant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Cuisine cuisine;

    private int defaultPreparationTime;

    @Column(nullable = false)
    private String contactEmail;

    private String picture;

    private List<ScheduleHour> scheduleHours;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id",nullable = false)
    private PersonJpaEntity owner;

    private Menu menu;

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getNameOfRestaurant() {
        return nameOfRestaurant;
    }

    public void setNameOfRestaurant(String nameOfRestaurant) {
        this.nameOfRestaurant = nameOfRestaurant;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public int getDefaultPreparationTime() {
        return defaultPreparationTime;
    }

    public void setDefaultPreparationTime(int defaultPreparationTime) {
        this.defaultPreparationTime = defaultPreparationTime;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PersonJpaEntity getOwner() {
        return owner;
    }

    public void setOwner(PersonJpaEntity owner) {
        this.owner = owner;
    }

    public List<ScheduleHour> getScheduleHours() {
        return scheduleHours;
    }

    public void setScheduleHours(List<ScheduleHour> scheduleHours) {
        this.scheduleHours = scheduleHours;
    }

    public RestaurantJpaEntity() {
    }
}
