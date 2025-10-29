package be.kdg.keepdishesgoing.customerOrder.domain;

import java.math.BigDecimal;

public class OrderItem {
    private OrderItemId orderItemId;
    private DishId dishId;
    private String dishName;
    private BigDecimal unitPrice;
    private int quantity;
    private String pictureUrl;

    public OrderItem(OrderItemId orderItemId, DishId dishId, String dishName,
                     BigDecimal unitPrice, int quantity, String pictureUrl) {
        this.orderItemId = orderItemId;
        this.dishId = dishId;
        this.dishName = dishName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.pictureUrl = pictureUrl;
    }

    public OrderItem(OrderItemId orderItemId, DishId dishId, String dishName,
                     double unitPrice, int quantity, String pictureUrl) {
        this(orderItemId, dishId, dishName, BigDecimal.valueOf(unitPrice), quantity, pictureUrl);
    }

    public OrderItemId getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(OrderItemId orderItemId) {
        this.orderItemId = orderItemId;
    }

    public DishId getDishId() {
        return dishId;
    }

    public void setDishId(DishId dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void increaseQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.quantity += amount;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
