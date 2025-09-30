package be.kdg.keepdishesgoing.restaurant.domain;

import be.kdg.keepdishesgoing.common.domain.Address;
import be.kdg.keepdishesgoing.common.domain.Person;
import be.kdg.keepdishesgoing.restaurant.domain.enums.Cuisine;
import be.kdg.keepdishesgoing.restaurant.domain.enums.OpeningStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Restaurant {

    private RestaurantId restaurantId;
    private String nameOfRestaurant;
    private Cuisine cuisine;
    private OpeningStatus openingStatus;
    private int defaultPreparationTime;
    private String contactEmail;
    private String picture;
    private Address address;
    private Person owner;
    private Menu menu;
    private List<ScheduleHour>  workingHours;

    private final List<Object> domainEvents = new ArrayList<>();

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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<ScheduleHour> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<ScheduleHour> workingHours) {
        this.workingHours = workingHours;
    }

    public OpeningStatus getOpeningStatus() {
        return openingStatus;
    }

    public void setOpeningStatus(OpeningStatus openingStatus) {
        this.openingStatus = openingStatus;
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }


    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getAveragePrice() {
        return menu.getDishIds().stream()
                .map(Dish::getCurrentPrice)
                .flatMap(Optional::stream)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

    }


    public void assignOwner(Person newOwner) {
        if (newOwner == null) {
            throw new IllegalArgumentException("Owner cannot be null");
        }
        this.owner = newOwner;

        domainEvents.add(newOwner);
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }

}
