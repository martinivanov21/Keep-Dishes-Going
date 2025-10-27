package be.kdg.keepdishesgoing.restaurant.adapter.out.menu;

import be.kdg.keepdishesgoing.restaurant.adapter.out.dish.DishJpaEntity;
import be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant.RestaurantJpaEntity;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "menus", schema = "kdg_restaurant")
public class MenuJpaEntity {

    @Id
    @Column(name = "menu_id", nullable = false)
    private UUID menuId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private RestaurantJpaEntity restaurant;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishJpaEntity> dishes;

    public MenuJpaEntity() {
    }

    public MenuJpaEntity(UUID menuId, List<DishJpaEntity> dishes) {
        this.menuId = menuId;
        this.dishes = dishes;
    }

    public MenuJpaEntity(UUID menuId, RestaurantJpaEntity restaurant, List<DishJpaEntity> dishes) {

        this.menuId = menuId;
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    public RestaurantJpaEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantJpaEntity restaurant) {
        this.restaurant = restaurant;
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
