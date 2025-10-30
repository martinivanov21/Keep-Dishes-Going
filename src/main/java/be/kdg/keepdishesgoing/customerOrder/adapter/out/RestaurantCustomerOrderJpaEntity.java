package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.domain.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurant_customer_order", schema = "kdg_customer_order")
public class RestaurantCustomerOrderJpaEntity {

    @Id
    @Column(name = "restaurant_id",nullable = false)
    private UUID restaurantId;

    private String restaurantName;

    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;

    private String pictureUrl;

    @Column(name = "delivery_street")
    private String deliveryStreet;

    @Column(name = "delivery_number")
    private int deliveryNumber;

    @Column(name = "delivery_city")
    private String deliveryCity;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    private MenuCustomerOrderJpaEntity menu;

    @ElementCollection
    @CollectionTable(
            name = "restaurant_working_hours",
            schema = "kdg_customer_order",
            joinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<WorkingHourCustomerEmbeddable> workingHours = new ArrayList<>();

    private BigDecimal averagePrice;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "price_range")
    private PriceRange priceRange;
    @Column(name = "guesstimated_delivery_time_minutes")
    private int guesstimatedDeliveryTimeMinutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "opening_status")
    private OpeningStatus openingStatus;

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
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

    public MenuCustomerOrderJpaEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuCustomerOrderJpaEntity menu) {
        this.menu = menu;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public PriceRange getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(PriceRange priceRange) {
        this.priceRange = priceRange;
    }

    public int getGuesstimatedDeliveryTimeMinutes() {
        return guesstimatedDeliveryTimeMinutes;
    }

    public void setGuesstimatedDeliveryTimeMinutes(int guesstimatedDeliveryTimeMinutes) {
        this.guesstimatedDeliveryTimeMinutes = guesstimatedDeliveryTimeMinutes;
    }


    public List<WorkingHourCustomerEmbeddable> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<WorkingHourCustomerEmbeddable> workingHours) {
        this.workingHours = workingHours;
    }

    public OpeningStatus getOpeningStatus() {
        return openingStatus;
    }

    public void setOpeningStatus(OpeningStatus openingStatus) {
        this.openingStatus = openingStatus;
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
}
