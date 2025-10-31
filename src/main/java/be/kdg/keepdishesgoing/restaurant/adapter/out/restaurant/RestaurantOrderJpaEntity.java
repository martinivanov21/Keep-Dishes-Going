package be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant;

import be.kdg.keepdishesgoing.restaurant.domain.enums.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurant_orders", schema = "kdg_restaurant")
public class RestaurantOrderJpaEntity {
    @Id
    @Column(name = "customer_order_id", nullable = false)
    private UUID customerOrderId;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "delivery_street", nullable = false)
    private String deliveryStreet;

    @Column(name = "delivery_number", nullable = false)
    private int deliveryNumber;

    @Column(name = "delivery_city", nullable = false)
    private String deliveryCity;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "estimated_time", nullable = false)
    private int estimatedTime;

    @Column(name = "submitted_time", nullable = false)
    private LocalDateTime submittedTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantOrderItemJpaEntity> items = new ArrayList<>();

    public RestaurantOrderJpaEntity() {
    }

    public UUID getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(UUID customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public LocalDateTime getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(LocalDateTime submittedTime) {
        this.submittedTime = submittedTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<RestaurantOrderItemJpaEntity> getItems() {
        return items;
    }

    public void setItems(List<RestaurantOrderItemJpaEntity> items) {
        this.items = items;
    }
}

