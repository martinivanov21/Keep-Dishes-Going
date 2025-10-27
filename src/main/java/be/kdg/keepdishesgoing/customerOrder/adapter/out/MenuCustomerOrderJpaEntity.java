package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "menu_customer_order", schema = "kdg_customer_order")
public class MenuCustomerOrderJpaEntity {

    @Id
    @Column(name = "menu_id", nullable = false)
    private UUID menuId;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishCustomerOrderJpaEntity> dishes;

    @Column(name = "restaurant_id")
    private UUID restaurantId;

    public MenuCustomerOrderJpaEntity() {
    }

    public UUID getMenuId() {
        return menuId;
    }

    public void setMenuId(UUID menuId) {
        this.menuId = menuId;
    }

    public List<DishCustomerOrderJpaEntity> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishCustomerOrderJpaEntity> dishes) {
        this.dishes = dishes;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }
}
