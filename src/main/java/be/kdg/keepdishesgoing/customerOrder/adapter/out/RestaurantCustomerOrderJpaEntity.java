package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.domain.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurant_customer_order", schema = "kdg_customerOrder")
public class RestaurantCustomerOrderJpaEntity {

    @Id
    @Column(name = "restaurant_id",nullable = false)
    private UUID restaurantId;

//    private AddressCustomerOrderJpaEntity address;
    private String restaurantName;
    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;
    private String pictureUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    private MenuCustomerOrderJpaEntity menu;
//    @OneToMany(mappedBy = "restaurant_customer_order")
//    private List<ScheduleHourCustomerOrderJpaEntity> scheduleHours;
    private BigDecimal averagePrice;
    private PriceRange priceRange;
    private int guesstimatedDeliveryTimeMinutes;
//    private List<DishCustomerOrderJpaEntity> publishedDishes;

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

//    public AddressCustomerOrderJpaEntity getAddress() {
//        return address;
//    }
//
//    public void setAddress(AddressCustomerOrderJpaEntity address) {
//        this.address = address;
//    }

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

//    public List<ScheduleHourCustomerOrderJpaEntity> getScheduleHours() {
//        return scheduleHours;
//    }
//
//    public void setScheduleHours(List<ScheduleHourCustomerOrderJpaEntity> scheduleHours) {
//        this.scheduleHours = scheduleHours;
//    }

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

}
