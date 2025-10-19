package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.domain.OrderStatus;
import jakarta.persistence.*;
import jdk.jfr.Name;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer_order", schema = "kdg_customerOrder" )
public class CustomerOrderJpaEntity {

    @Id
    @Column(name = "customer_order_id", nullable = false)
    private UUID customerOrderId;

    @Column(nullable = false, name = "estimated_time")
    private double estimateTime;
    @Column(nullable = false, name = "total_price")
    private double totalPrice;
    @Column(nullable = false)
    private LocalDateTime submittedTime;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToOne(cascade = CascadeType.ALL)
    private AddressJpaEntity deliveryAddress;

    public CustomerOrderJpaEntity() {
    }



    public UUID getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(UUID customerOrderId) {
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

    public AddressJpaEntity getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(AddressJpaEntity deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
