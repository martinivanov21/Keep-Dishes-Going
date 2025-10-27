package be.kdg.keepdishesgoing.customerOrder.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Basket {
    private BasketId basketId;
    private RestaurantId restaurantId;
    private List<BasketItem> item;
    private BasketStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Basket(BasketId basketId) {
        this.basketId = basketId;
        this.restaurantId = null;
        this.item = new ArrayList<>();
        this.status = BasketStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Basket(BasketId basketId, RestaurantId restaurantId, List<BasketItem> item, BasketStatus status,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.basketId = basketId;
        this.restaurantId = restaurantId;
        this.item = item;
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

    public List<BasketItem> getItem() {
        return item;
    }

    public void setItem(List<BasketItem> item) {
        this.item = item;
    }

    public BasketStatus getStatus() {
        return status;
    }

    public void setStatus(BasketStatus status) {
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

    public void addItem(Dish dish, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        if (this.restaurantId == null) {
            this.restaurantId = dish.getMenu().getRestaurantId();
        }

        if (!dish.getMenu().getRestaurantId().equals(this.restaurantId)) {
            throw new IllegalArgumentException(
                    "Cannot add dishes from different restaurants. Please clear your basket to order from a different restaurant.");
        }

        if (dish.getStatus() != DishStatus.PUBLISHED || dish.getQuantity() <= 0) {
            throw new IllegalArgumentException("Dish is not available");
        }

        BasketItem existingItem = findItemByDishId(dish.getDishId());

        if (existingItem != null) {
            existingItem.increaseQuantity(quantity);
        } else {
            BasketItem newItem = new BasketItem(
                    new BasketItemId(UUID.randomUUID()),
                    dish.getDishId(),
                    dish.getNameOfDish(),
                    dish.getPrice(),
                    quantity,
                    dish.getPictureUrl()
            );
            this.items.add(newItem);
        }

        this.updatedAt = LocalDateTime.now();
    }

    public void removeItem(DishId dishId) {
        this.items.removeIf(item -> item.getDishId().equals(dishId));

        if (this.items.isEmpty()) {
            this.restaurantId = null;
        }

        this.updatedAt = LocalDateTime.now();
    }

    public void updateItemQuantity(DishId dishId, int newQuantity) {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        if (newQuantity == 0) {
            removeItem(dishId);
            return;
        }

        BasketItem item = findItemByDishId(dishId);
        if (item == null) {
            throw new IllegalArgumentException("Item not found in basket");
        }

        item.setQuantity(newQuantity);
        this.updatedAt = LocalDateTime.now();
    }

    public void clear() {
        this.items.clear();
        this.restaurantId = null;
        this.updatedAt = LocalDateTime.now();
    }

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(BasketItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getTotalItemCount() {
        return items.stream()
                .mapToInt(BasketItem::getQuantity)
                .sum();
    }

    public boolean canCheckout() {
        return !items.isEmpty() && restaurantId != null && status == BasketStatus.ACTIVE;
    }

    private BasketItem findItemByDishId(DishId dishId) {
        return items.stream()
                .filter(item -> item.getDishId().equals(dishId))
                .findFirst()
                .orElse(null);
    }


}
