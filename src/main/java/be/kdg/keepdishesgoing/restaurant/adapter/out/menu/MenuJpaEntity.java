package be.kdg.keepdishesgoing.restaurant.adapter.out.menu;

import be.kdg.keepdishesgoing.restaurant.adapter.out.dish.DishJpaEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "menus")
public class MenuJpaEntity {

    @Id
    @Column(name = "menu_id", nullable = false)
    private UUID menuId;

    // many dishes can participate in many menus
    @ManyToMany
    @JoinTable(
            name = "menu_dishes",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<DishJpaEntity> dishes;

}
