package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "order_item", schema = "kdg_customer_order")
public class OrderItemJpaEntity {

    @Id
    @Column(name = "order_item_id")
    private UUID orderItemId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer_order_id", nullable = true)
    private CustomerOrderJpaEntity customerOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "basket_id", nullable = true)
    private BasketJpaEntity basket;

    @Column(name = "dish_id", nullable = false)
    private UUID dishId;

    @Column(name = "dish_name", nullable = false)
    private String dishName;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "picture_url")
    private String pictureUrl;

    public OrderItemJpaEntity() {
    }

    public CustomerOrderJpaEntity getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrderJpaEntity customerOrder) {
        this.customerOrder = customerOrder;
        if (customerOrder != null) {
            this.basket = null;
        }
    }

    public UUID getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(UUID orderItemId) {
        this.orderItemId = orderItemId;
    }

    public BasketJpaEntity getBasket() {
        return basket;
    }

    public void setBasket(BasketJpaEntity basket) {
        this.basket = basket;
        if (basket != null) {
            this.customerOrder = null;
        }
    }

    public UUID getDishId() {
        return dishId;
    }

    public void setDishId(UUID dishId) {
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
