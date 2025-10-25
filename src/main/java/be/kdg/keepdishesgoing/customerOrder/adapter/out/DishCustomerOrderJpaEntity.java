package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "dish_customer_order", schema = "kdg_customer_order")
public class DishCustomerOrderJpaEntity {

    @Id
    @Column(name = "dish_id")
    private UUID dishId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuCustomerOrderJpaEntity menu;

    public DishCustomerOrderJpaEntity() {
    }

    public UUID getDishId() {
        return dishId;
    }

    public void setDishId(UUID dishId) {
        this.dishId = dishId;
    }

    public MenuCustomerOrderJpaEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuCustomerOrderJpaEntity menu) {
        this.menu = menu;
    }
}
