package be.kdg.keepdishesgoing.customerOrder.domain;


import org.jmolecules.event.types.DomainEvent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Restaurant {
    private RestaurantId restaurantId;
    private String restaurantName;
    private Address deliveryAddress;
    private Cuisine cuisine;
    private String pictureUrl;
    private Menu menu;
    private List<ScheduleHour> workingHours;
    private PriceRange priceRange;
    private BigDecimal averagePrice;
    private int guesstimatedDeliveryTimeMinutes;
    private List<Dish> publishedDishes;

    private final List<DomainEvent> eventStore = new ArrayList<>();
    private final List<DomainEvent> uncommitedEvents = new ArrayList<>();

    public Restaurant(RestaurantId restaurantId, String restaurantName, Address deliveryAddress, Cuisine cuisine,
                      String pictureUrl, Menu menu, List<ScheduleHour> workingHours, PriceRange priceRange,
                      BigDecimal averagePrice, int guesstimatedDeliveryTimeMinutes, List<Dish> publishedDishes) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.deliveryAddress = deliveryAddress;
        this.cuisine = cuisine;
        this.pictureUrl = pictureUrl;
        this.menu = menu;
        this.workingHours = workingHours;
        this.priceRange = priceRange;
        this.averagePrice = averagePrice;
        this.guesstimatedDeliveryTimeMinutes = guesstimatedDeliveryTimeMinutes;
        this.publishedDishes = publishedDishes;
        this.eventStore.addAll(eventStore);
    }

    public List<Dish> getPublishedDishes() {
        return publishedDishes;
    }

    public void setPublishedDishes(List<Dish> publishedDishes) {
        this.publishedDishes = publishedDishes;
    }

    public void commitEvents() {
        eventStore.addAll(uncommitedEvents);
        uncommitedEvents.clear();
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public int getGuesstimatedDeliveryTimeMinutes() {
        return guesstimatedDeliveryTimeMinutes;
    }

    public void setGuesstimatedDeliveryTimeMinutes(int guesstimatedDeliveryTimeMinutes) {
        this.guesstimatedDeliveryTimeMinutes = guesstimatedDeliveryTimeMinutes;
    }

    public List<DomainEvent> getDomainEvents() {
        return new ArrayList<>(
                Stream.concat(eventStore.stream(), uncommitedEvents.stream()).toList());
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    public PriceRange getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(PriceRange priceRange) {
        this.priceRange = priceRange;
    }

    public List<DomainEvent> getEventStore() {
        return eventStore;
    }

    public List<DomainEvent> getUncommitedEvents() {
        return uncommitedEvents;
    }
}

