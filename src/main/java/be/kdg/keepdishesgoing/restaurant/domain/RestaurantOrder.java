package be.kdg.keepdishesgoing.restaurant.domain;

import be.kdg.keepdishesgoing.restaurant.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class RestaurantOrder {
    private final UUID customerOrderId;
    private final UUID restaurantId;

    private String customerName;
    private String customerEmail;

    private String deliveryStreet;
    private int deliveryNumber;
    private String deliveryCity;

    private BigDecimal totalPrice;
    private int estimatedTime;
    private LocalDateTime submittedTime;
    private OrderStatus orderStatus;

    private final List<Item> items = new ArrayList<>();

    public RestaurantOrder(UUID customerOrderId, UUID restaurantId) {
        this.customerOrderId = customerOrderId;
        this.restaurantId = restaurantId;
    }

    public static RestaurantOrder submitted(UUID orderId, UUID restaurantId,
                                            String name, String email,
                                            String street, int number, String city,
                                            BigDecimal total, int eta, LocalDateTime submitted,
                                            List<Item> items) {
        var ro = new RestaurantOrder(orderId, restaurantId);
        ro.customerName = name;
        ro.customerEmail = email;
        ro.deliveryStreet = street;
        ro.deliveryNumber = number;
        ro.deliveryCity = city;
        ro.totalPrice = total;
        ro.estimatedTime = eta;
        ro.submittedTime = submitted;
        ro.orderStatus = OrderStatus.ACCEPTED;
        ro.items.clear();
        ro.items.addAll(items);
        return ro;
    }

    public void applyQuantityChange(UUID dishId, int newQty, BigDecimal lineTotal, BigDecimal orderTotal) {
        var it = items.stream().filter(i -> i.dishId.equals(dishId)).findFirst()
                .orElseThrow(() -> new IllegalStateException("Dish not found in order projection"));
        it.quantity = newQty;
        it.lineTotal = lineTotal;
        this.totalPrice = orderTotal;
    }

    public void accept(int finalEta) {
        this.orderStatus = OrderStatus.ACCEPTED;
        this.estimatedTime = finalEta;
    }

    public void decline() {
        this.orderStatus = OrderStatus.REJECTED;
    }


    public UUID getCustomerOrderId() { return customerOrderId; }
    public UUID getRestaurantId() { return restaurantId; }
    public List<Item> getItems() { return Collections.unmodifiableList(items); }
    public OrderStatus getOrderStatus() { return orderStatus; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public int getEstimatedTime() { return estimatedTime; }
    public LocalDateTime getSubmittedTime() { return submittedTime; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public String getDeliveryStreet() { return deliveryStreet; }
    public int getDeliveryNumber() { return deliveryNumber; }
    public String getDeliveryCity() { return deliveryCity; }

    public static class Item {
        private UUID dishId;
        private String dishName;
        private int quantity;
        private BigDecimal unitPrice;
        private BigDecimal lineTotal;

        public Item(UUID dishId, String dishName, int quantity, BigDecimal unitPrice, BigDecimal lineTotal) {
            this.dishId = dishId;
            this.dishName = dishName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.lineTotal = lineTotal;
        }

        public UUID getDishId() { return dishId; }
        public String getDishName() { return dishName; }
        public int getQuantity() { return quantity; }
        public BigDecimal getUnitPrice() { return unitPrice; }
        public BigDecimal getLineTotal() { return lineTotal; }
    }
}
