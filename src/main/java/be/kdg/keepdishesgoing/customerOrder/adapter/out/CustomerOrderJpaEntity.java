package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.domain.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer_order", schema = "kdg_customer_order" )
public class CustomerOrderJpaEntity {

    @Id
    @Column(name = "customer_order_id", nullable = false)
    private UUID customerOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id", nullable = false)
    private RestaurantCustomerOrderJpaEntity restaurant;

    @Column(nullable = false, name = "estimated_time")
    private int estimateTime;
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;
    @Column(nullable = false)
    private LocalDateTime submittedTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "delivery_street", nullable = false)
    private String deliveryStreet;
    @Column(name = "delivery_number", nullable = false)
    private int deliveryNumber;
    @Column(name = "delivery_city", nullable = false)
    private String deliveryCity;

    private String customerName;

    private String customerEmail;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemJpaEntity> orderItems = new ArrayList<>();

    public CustomerOrderJpaEntity() {
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

    public UUID getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(UUID customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public RestaurantCustomerOrderJpaEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantCustomerOrderJpaEntity restaurant) {
        this.restaurant = restaurant;
    }

    public int getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(int estimateTime) {
        this.estimateTime = estimateTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    public List<OrderItemJpaEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemJpaEntity> orderItems) {
        this.orderItems = orderItems;
    }
}
