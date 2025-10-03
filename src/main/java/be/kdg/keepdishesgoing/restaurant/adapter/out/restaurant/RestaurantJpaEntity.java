package be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant;

import be.kdg.keepdishesgoing.restaurant.adapter.out.RestaurantEventJpaEntity;
import be.kdg.keepdishesgoing.restaurant.adapter.out.address.AddressJpaEntity;
import be.kdg.keepdishesgoing.restaurant.adapter.out.menu.MenuJpaEntity;
import be.kdg.keepdishesgoing.restaurant.adapter.out.owner.OwnerJpaEntity;
import be.kdg.keepdishesgoing.restaurant.domain.Menu;
import be.kdg.keepdishesgoing.restaurant.domain.enums.Cuisine;
import be.kdg.keepdishesgoing.restaurant.domain.enums.OpeningStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurant", schema = "kdg_restaurant")
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

    @Enumerated(EnumType.STRING)
    private OpeningStatus openingStatus;

    @OneToMany(mappedBy = "restaurant")
    private List<ScheduleHourJpaEntity> workingHours = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressJpaEntity address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id",nullable = false)
    private OwnerJpaEntity owner;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantEventJpaEntity> events = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    private MenuJpaEntity menu;

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


    public OwnerJpaEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerJpaEntity owner) {
        this.owner = owner;
    }

    public RestaurantJpaEntity() {
    }

    public OpeningStatus getOpeningStatus() {
        return openingStatus;
    }

    public void setOpeningStatus(OpeningStatus openingStatus) {
        this.openingStatus = openingStatus;
    }

    public List<ScheduleHourJpaEntity> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<ScheduleHourJpaEntity> workingHours) {
        this.workingHours = workingHours;
    }

    public AddressJpaEntity getAddress() {
        return address;
    }

    public void setAddress(AddressJpaEntity address) {
        this.address = address;
    }

    public List<RestaurantEventJpaEntity> getEvents() {
        return events;
    }

    public void setEvents(List<RestaurantEventJpaEntity> events) {
        this.events = events;
    }

    public MenuJpaEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuJpaEntity menu) {
        this.menu = menu;
    }
}
