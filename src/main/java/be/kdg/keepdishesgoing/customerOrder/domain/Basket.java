package be.kdg.keepdishesgoing.customerOrder.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Basket {
    private BasketId basketId;
    private RestaurantId restaurantId;
    private List<OrderItem> items;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Basket(BasketId basketId) {
        this.basketId = basketId;
        this.restaurantId = null;
        this.items = new ArrayList<>();
        this.status = OrderStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Basket(BasketId basketId, RestaurantId restaurantId, List<OrderItem> items, OrderStatus status,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.basketId = basketId;
        this.restaurantId = restaurantId;
        this.items = items != null ? items : new ArrayList<>();
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public BasketId getBasketId() {
        return basketId;
    }

    public void setBasketId(BasketId basketId) {
        this.basketId = basketId;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> item) {
        this.items = item;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public void addItem(DishId dishId, String dishName, double price, String pictureUrl,
                        int quantity, RestaurantId restaurantId) {
        validateQuantity(quantity);
        lockToRestaurantIfFirst(restaurantId);
        validateSameRestaurant(restaurantId);

        OrderItem existingItem = findItemByDishId(dishId);

        if (existingItem != null) {
            existingItem.increaseQuantity(quantity);
        } else {
            OrderItem newItem = new OrderItem(
                    new OrderItemId(UUID.randomUUID()),
                    dishId,
                    dishName,
                    price,
                    quantity,
                    pictureUrl
            );
            this.items.add(newItem);
        }

        touch();
    }

    public void removeItem(DishId dishId) {
        this.items.removeIf(item -> item.getDishId().equals(dishId));

        if (this.items.isEmpty()) {
            unlockRestaurant();
        }

        touch();
    }

    public void updateItemQuantity(DishId dishId, int newQuantity) {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        if (newQuantity == 0) {
            removeItem(dishId);
            return;
        }

        OrderItem item = findItemByDishId(dishId);
        if (item == null) {
            throw new IllegalArgumentException("Item not found in basket");
        }

        item.setQuantity(newQuantity);
        touch();
    }

    public void clear() {
        this.items.clear();
        unlockRestaurant();
        touch();
    }

    // In Basket class
    public CustomerOrder checkout(String deliveryStreet,
                                  int deliveryNumber, String deliveryCity,
                                  int estimatedDeliveryTimeMinutes) {
        if (!canCheckout()) {
            throw new IllegalStateException("Basket is not ready for checkout. " +
                    "Ensure basket has items and is linked to a restaurant.");
        }

        if (deliveryStreet == null || deliveryStreet.trim().isEmpty()) {
            throw new IllegalArgumentException("Delivery street is required");
        }
        if (deliveryCity == null || deliveryCity.trim().isEmpty()) {
            throw new IllegalArgumentException("Delivery city is required");
        }
        if (deliveryNumber <= 0) {
            throw new IllegalArgumentException("Delivery number must be positive");
        }

        CustomerOrder order = new CustomerOrder(
                new CustomerOrderId(UUID.randomUUID()),
                this.restaurantId,
                new ArrayList<>(this.items),
                this.getTotalPrice(),
                estimatedDeliveryTimeMinutes,
                LocalDateTime.now(),
                OrderStatus.ACTIVE,
                deliveryStreet,
                deliveryNumber,
                deliveryCity
        );

        this.status = OrderStatus.PENDING;
        touch();

        return order;
    }

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getTotalItemCount() {
        return items.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }


    public boolean canCheckout() {
        return !items.isEmpty()
                && restaurantId != null
                && status == OrderStatus.ACTIVE;
    }
    public boolean isEmpty() {
        return items.isEmpty();
    }

    private void lockToRestaurantIfFirst(RestaurantId restaurantId) {
        if (this.restaurantId == null) {
            this.restaurantId = restaurantId;
        }
    }
    private void unlockRestaurant() {
        this.restaurantId = null;
    }

    private void validateSameRestaurant(RestaurantId restaurantId) {
        if (this.restaurantId != null && !this.restaurantId.equals(restaurantId)) {
            throw new IllegalArgumentException(
                    "Cannot add dishes from different restaurants. " +
                            "Please clear your basket to order from a different restaurant.");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }

    private OrderItem findItemByDishId(DishId dishId) {
        return items.stream()
                .filter(item -> item.getDishId().equals(dishId))
                .findFirst()
                .orElse(null);
    }

    private OrderItem createOrderItem(Dish dish, int quantity) {
        return new OrderItem(
                new OrderItemId(UUID.randomUUID()),
                dish.getDishId(),
                dish.getNameOfDish(),
                dish.getPrice(),
                quantity,
                dish.getPictureUrl()
        );
    }

    private void touch() {
        this.updatedAt = LocalDateTime.now();
    }



}
