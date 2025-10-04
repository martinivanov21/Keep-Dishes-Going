package be.kdg.keepdishesgoing.customerOrder.domain;


//import be.kdg.keepdishesgoing.restaurant.domain.Address;
//import be.kdg.keepdishesgoing.restaurant.domain.Owner;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerOrder {
    private CustomerOrderId customerOrderId;
    private double estimateTime;
    private double totalPrice;
    private LocalDateTime submittedTime;
    private OrderStatus orderStatus;
//    private Owner customer;
//    private Address deliveryAddress;


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

}
