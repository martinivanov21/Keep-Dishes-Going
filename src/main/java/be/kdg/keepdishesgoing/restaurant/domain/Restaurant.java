package be.kdg.keepdishesgoing.restaurant.domain;

import be.kdg.keepdishesgoing.common.events.DomainEvent;
import be.kdg.keepdishesgoing.common.events.RestaurantCreateEvent;
import be.kdg.keepdishesgoing.common.events.RestaurantOpeningStatusChangedEvent;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.ScheduleHourDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.WorkingHourDto;
import be.kdg.keepdishesgoing.restaurant.domain.enums.Cuisine;
import be.kdg.keepdishesgoing.restaurant.domain.enums.OpeningStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Restaurant {

    private RestaurantId restaurantId;
    private String nameOfRestaurant;
    private Cuisine cuisine;
    private OpeningStatus openingStatus;
    private int defaultPreparationTime;
    private String contactEmail;
    private String picture;
    private AddressId addressId;
    private OwnerId ownerId;
    private MenuId menuId;
    private List<WorkingHour>  workingHours;

    private final List<DomainEvent> eventStore = new ArrayList<>();
    private final List<DomainEvent> uncommitedEvents = new ArrayList<>();




    public Restaurant(RestaurantId restaurantId, String nameOfRestaurant, Cuisine cuisine,
                      OpeningStatus openingStatus, int defaultPreparationTime, String contactEmail,
                      String picture, AddressId addressId, OwnerId ownerId, MenuId menuId,
                      List<WorkingHour> workingHours, Address address) {
        this.restaurantId = restaurantId;
        this.nameOfRestaurant = nameOfRestaurant;
        this.cuisine = cuisine;
        this.openingStatus = openingStatus;
        this.defaultPreparationTime = defaultPreparationTime;
        this.contactEmail = contactEmail;
        this.picture = picture;
        this.addressId = addressId;
        this.ownerId = ownerId;
        this.menuId = menuId;
        this.workingHours = workingHours != null ? workingHours : new ArrayList<>();

        this.raiseEvent(new RestaurantCreateEvent(
                restaurantId.uuid(),
                nameOfRestaurant,
                picture,
                cuisine.name(),
                defaultPreparationTime,
                contactEmail,
                address != null ? address.getStreet() : "",
                address != null ? address.getNumber() : 0,
                address != null ? address.getCity() : "",
                openingStatus.name(),
                workingHours.stream()
                        .map(wh -> new WorkingHourDto(
                                wh.getDayOfWeek().toString(),
                                wh.getOpeningTime().toString(),
                                wh.getClosingTime().toString()
                        )).toList(),
                menuId.uuid(),
                LocalDateTime.now()
        ));
    }
    public Restaurant(RestaurantId restaurantId, String nameOfRestaurant, Cuisine cuisine,
                      OpeningStatus openingStatus, int defaultPreparationTime, String contactEmail,
                      String picture, AddressId addressId, OwnerId ownerId, MenuId menuId,
                      List<WorkingHour> workingHours) {
        this.restaurantId = restaurantId;
        this.nameOfRestaurant = nameOfRestaurant;
        this.cuisine = cuisine;
        this.openingStatus = openingStatus;
        this.defaultPreparationTime = defaultPreparationTime;
        this.contactEmail = contactEmail;
        this.picture = picture;
        this.addressId = addressId;
        this.ownerId = ownerId;
        this.menuId = menuId;
        this.workingHours = (workingHours != null) ? workingHours : new ArrayList<>();
    }

    public void raiseRestaurantCreatedEvent(String street, int number, String city) {
        this.raiseEvent(new RestaurantCreateEvent(
                restaurantId.uuid(),
                nameOfRestaurant,
                picture,
                cuisine.name(),
                defaultPreparationTime,
                contactEmail,
                street,
                number,
                city,
                openingStatus.name(),
                workingHours.stream()
                        .map(wh -> new WorkingHourDto(
                                wh.getDayOfWeek().name(),
                                wh.getOpeningTime().toString(),
                                wh.getClosingTime().toString()
                        )).toList(),
                menuId != null ? menuId.uuid() : null,
                LocalDateTime.now()
        ));
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public String getNameOfRestaurant() {
        return nameOfRestaurant;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public OpeningStatus getOpeningStatus() {
        return openingStatus;
    }

    public int getDefaultPreparationTime() {
        return defaultPreparationTime;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public MenuId getMenuId() {
        return menuId;
    }

    public String getPicture() {
        return picture;
    }

    public AddressId getAddressId() {
        return addressId;
    }

    public OwnerId getOwnerId() {
        return ownerId;
    }


    public List<WorkingHour> getWorkingHours() {
        return workingHours;
    }

    public void commitEvents() {
        eventStore.addAll(uncommitedEvents);
        uncommitedEvents.clear();
    }


    public List<DomainEvent> getUncommitedEvents() {
        return uncommitedEvents;
    }

    public List<DomainEvent> getEventStore() {
        return eventStore;
    }

    public List<DomainEvent> getDomainEvents() {
        return new ArrayList<>(
                Stream.concat(eventStore.stream(), uncommitedEvents.stream()).toList());
    }

    public void raiseEvent(DomainEvent event) {
        this.uncommitedEvents.add(event);
    }

    public void changeOpeningStatus(OpeningStatus newStatus) {
        if (this.openingStatus != newStatus) {
            this.openingStatus = newStatus;
            raiseEvent(new RestaurantOpeningStatusChangedEvent(
                    this.restaurantId.uuid(),
                    newStatus.name(),
                    LocalDateTime.now()
            ));
        }
    }
    public void clearEvents() {
        uncommitedEvents.clear();
    }


}
