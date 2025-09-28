package be.kdg.keepdishesgoing.restaurant.domain;

import be.kdg.keepdishesgoing.common.domain.Address;
import be.kdg.keepdishesgoing.common.domain.Person;
import be.kdg.keepdishesgoing.restaurant.domain.enums.Cusine;
import be.kdg.keepdishesgoing.restaurant.domain.enums.OpeningStatus;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Restaurant {

    private RestaurantId restaurantId;
    private String nameOfRestaurant;
    private ScheduleHour openingHours;
    private ScheduleHour closingHours;
    private Cusine cusine;
    private OpeningStatus openingStatus;
    private String contactEmail;
    private String picture;
    private Address address;
    private Person owner;
    private List<Dish> dishes;

    private final List<Object> domainEvents = new ArrayList<>();

    public String getNameOfRestaurant() {
        return nameOfRestaurant;
    }

    public void setNameOfRestaurant(String nameOfRestaurant) {
        this.nameOfRestaurant = nameOfRestaurant;
    }

    public ScheduleHour getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(ScheduleHour openingHours) {
        this.openingHours = openingHours;
    }

    public ScheduleHour getClosingHours() {
        return closingHours;
    }

    public void setClosingHours(ScheduleHour closingHours) {
        this.closingHours = closingHours;
    }

    public Cusine getCusine() {
        return cusine;
    }

    public void setCusine(Cusine cusine) {
        this.cusine = cusine;
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

    public double getAveragePrice() {
        return dishes.stream()
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
