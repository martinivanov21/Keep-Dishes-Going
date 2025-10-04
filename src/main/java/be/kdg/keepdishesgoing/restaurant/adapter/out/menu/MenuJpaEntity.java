package be.kdg.keepdishesgoing.restaurant.adapter.out.menu;

import be.kdg.keepdishesgoing.restaurant.adapter.out.dish.DishJpaEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "menus", schema = "kdg_restaurant")
public class MenuJpaEntity {

    @Id
    @Column(name = "menu_id", nullable = false)
    private UUID menuId;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishJpaEntity> dishes;

    public MenuJpaEntity() {
    }

    public UUID getMenuId() {
        return menuId;
    }

    public void setMenuId(UUID menuId) {
        this.menuId = menuId;
    }


    public void setDishes(List<DishJpaEntity> dishes) {
        this.dishes = dishes;
    }

    public List<DishJpaEntity> getDishes() {
        return dishes;
    }
}
