package be.kdg.keepdishesgoing.restaurant.domain;

import be.kdg.keepdishesgoing.restaurant.domain.enums.Cuisine;
import be.kdg.keepdishesgoing.restaurant.domain.enums.OpeningStatus;
import org.jmolecules.event.types.DomainEvent;

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
    private Menu menu;
    private List<ScheduleHour>  workingHours;

    private final List<DomainEvent> eventStore = new ArrayList<>();
    private final List<DomainEvent> uncommitedEvents = new ArrayList<>();




    public Restaurant(RestaurantId restaurantId, String nameOfRestaurant, Cuisine cuisine, OpeningStatus openingStatus,
                      int defaultPreparationTime, String contactEmail, String picture, AddressId addressId,
                      OwnerId ownerId, Menu menu, List<ScheduleHour> workingHours) {
        this.restaurantId = restaurantId;
        this.nameOfRestaurant = nameOfRestaurant;
        this.cuisine = cuisine;
        this.openingStatus = openingStatus;
        this.defaultPreparationTime = defaultPreparationTime;
        this.contactEmail = contactEmail;
        this.picture = picture;
        this.addressId = addressId;
        this.ownerId = ownerId;
        this.menu = menu;
        this.workingHours = workingHours;
        this.eventStore.addAll(eventStore);
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

    public String getPicture() {
        return picture;
    }

    public AddressId getAddressId() {
        return addressId;
    }

    public OwnerId getOwnerId() {
        return ownerId;
    }

    public Menu getMenu() {
        return menu;
    }

    public List<ScheduleHour> getWorkingHours() {
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


    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", nameOfRestaurant='" + nameOfRestaurant + '\'' +
                ", cuisine=" + cuisine +
                ", openingStatus=" + openingStatus +
                ", defaultPreparationTime=" + defaultPreparationTime +
                ", contactEmail='" + contactEmail + '\'' +
                ", picture='" + picture + '\'' +
                ", addressId=" + addressId +
                ", ownerId=" + ownerId +
                ", menu=" + menu +
                ", workingHours=" + workingHours +
                ", eventStore=" + eventStore +
                ", uncommitedEvents=" + uncommitedEvents +
                '}';
    }
}
