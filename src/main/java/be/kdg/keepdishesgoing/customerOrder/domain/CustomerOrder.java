package be.kdg.keepdishesgoing.customerOrder.domain;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CustomerOrder {
    private CustomerOrderId customerOrderId;
    private RestaurantId restaurantId;
    private List<OrderItem> orderItem;
    private double estimateTime;
    private double totalPrice;
    private LocalDateTime submittedTime;
    private OrderStatus orderStatus;
    private Address deliveryAddress;

    public CustomerOrder(CustomerOrderId customerOrderId, double estimateTime, double totalPrice,
                         LocalDateTime submittedTime, OrderStatus orderStatus, Address deliveryAddress) {
        this.customerOrderId = customerOrderId;
        this.estimateTime = estimateTime;
        this.totalPrice = totalPrice;
        this.submittedTime = submittedTime;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public CustomerOrderId getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(CustomerOrderId customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public double getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(double estimateTime) {
        this.estimateTime = estimateTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
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

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double calculateTotal() {
        return orderItems.stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();
    }
}
