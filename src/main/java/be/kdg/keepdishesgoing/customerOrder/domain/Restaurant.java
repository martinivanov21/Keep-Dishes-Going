package be.kdg.keepdishesgoing.customerOrder.domain;


import org.jmolecules.event.types.DomainEvent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Restaurant {
    private RestaurantId restaurantId;
    private String restaurantName;
    private Cuisine cuisine;
    private String pictureUrl;
    private List<ScheduleHour> workingHours;
    private PriceRange priceRange;
    private BigDecimal averagePrice;
    private int guesstimatedDeliveryTimeMinutes;
    private MenuId menuId;
    private OpeningStatus openingStatus;

    private String deliveryStreet;
    private int deliveryNumber;
    private String deliveryCity;

    public Restaurant(RestaurantId restaurantId, String restaurantName, Cuisine cuisine,
                      String pictureUrl, List<ScheduleHour> workingHours, PriceRange priceRange,
                      BigDecimal averagePrice, int guesstimatedDeliveryTimeMinutes, MenuId menuId,
                      OpeningStatus openingStatus, String deliveryStreet, int deliveryNumber, String deliveryCity) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.cuisine = cuisine;
        this.pictureUrl = pictureUrl;
        this.workingHours = workingHours;
        this.priceRange = priceRange;
        this.averagePrice = averagePrice;
        this.guesstimatedDeliveryTimeMinutes = guesstimatedDeliveryTimeMinutes;
        this.menuId = menuId;
        this.openingStatus = openingStatus;
        this.deliveryStreet = deliveryStreet;
        this.deliveryNumber = deliveryNumber;
        this.deliveryCity = deliveryCity;
    }

    public MenuId getMenuId() {
        return menuId;
    }

    public void setMenuId(MenuId menuId) {
        this.menuId = menuId;
    }


    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(int deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public OpeningStatus getOpeningStatus() {
        return openingStatus;
    }

    public void setOpeningStatus(OpeningStatus openingStatus) {
        this.openingStatus = openingStatus;
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


}

