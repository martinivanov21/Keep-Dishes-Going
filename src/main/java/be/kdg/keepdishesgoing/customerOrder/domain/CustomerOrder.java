package be.kdg.keepdishesgoing.customerOrder.domain;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerOrder {
    private CustomerOrderId customerOrderId;
    private RestaurantId restaurantId;
    private List<OrderItem> orderItems;

    private String customerName;
    private String customerEmail;


    private BigDecimal totalPrice;
    private int estimateTime;
    private LocalDateTime submittedTime;
    private OrderStatus orderStatus;

    private String deliveryStreet;
    private int deliveryStreetNumber;
    private String deliveryCity;


    public CustomerOrder(CustomerOrderId customerOrderId, RestaurantId restaurantId, List<OrderItem> orderItems,
                         BigDecimal totalPrice, int estimateTime, LocalDateTime submittedTime,
                         OrderStatus orderStatus, String deliveryStreet, int deliveryStreetNumber, String deliveryCity) {
        this.customerOrderId = customerOrderId;
        this.restaurantId = restaurantId;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.estimateTime = estimateTime;
        this.submittedTime = submittedTime;
        this.orderStatus = orderStatus;
        this.deliveryStreet = deliveryStreet;
        this.deliveryStreetNumber = deliveryStreetNumber;
        this.deliveryCity = deliveryCity;
    }

    public CustomerOrder(CustomerOrderId customerOrderId, RestaurantId restaurantId,
                         List<OrderItem> orderItems, String customerName, String customerEmail,
                         BigDecimal totalPrice, int estimatedDeliveryTimeMinutes,
                         LocalDateTime submittedTime, OrderStatus orderStatus,
                         String deliveryStreet, int deliveryStreetNumber, String deliveryCity) {
        this.customerOrderId = customerOrderId;
        this.restaurantId = restaurantId;
        this.orderItems = orderItems != null ? new ArrayList<>(orderItems) : new ArrayList<>();
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.totalPrice = totalPrice;
        this.estimateTime = estimatedDeliveryTimeMinutes;
        this.submittedTime = submittedTime;
        this.orderStatus = orderStatus;
        this.deliveryStreet = deliveryStreet;
        this.deliveryStreetNumber = deliveryStreetNumber;
        this.deliveryCity = deliveryCity;
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

    public int getDeliveryStreetNumber() {
        return deliveryStreetNumber;
    }

    public void setDeliveryStreetNumber(int deliveryStreetNumber) {
        this.deliveryStreetNumber = deliveryStreetNumber;
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

    public CustomerOrderId getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(CustomerOrderId customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public int getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(int estimateTime) {
        this.estimateTime = estimateTime;
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

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    public String getFullDeliveryAddress() {
        return String.format("%s %d, %s", deliveryStreet, deliveryStreetNumber, deliveryCity);
    }


    public int getTotalItemCount() {
        return orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }
}
