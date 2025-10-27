package be.kdg.keepdishesgoing.customerOrder.domain;

public class OrderItem {
    private OrderItemId orderItemId;
    private DishId dishId;
    private String dishName;
    private double unitPrice;
    private int quantity;

    public OrderItem(OrderItemId orderItemId, DishId dishId, String dishName, double unitPrice, int quantity) {
        this.orderItemId = orderItemId;
        this.dishId = dishId;
        this.dishName = dishName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return unitPrice * quantity;
    }
}
