package be.kdg.keepdishesgoing.restaurant.domain;

import java.util.List;

public class Menu {

    private MenuId menuId;

    private Restaurant restaurant;

    private List<DishId> dishIds;


    public Menu(MenuId menuId, Restaurant restaurant, List<DishId> dishIds) {
        this.menuId = menuId;
        this.restaurant = restaurant;
        this.dishIds = dishIds;
    }

    public Menu(MenuId menuId, List<DishId> dishIds) {
        this.menuId = menuId;
        this.dishIds = dishIds;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public MenuId getMenuId() {
        return menuId;
    }

    public void setMenuId(MenuId menuId) {
        this.menuId = menuId;
    }

    public List<DishId> getDishIds() {
        return dishIds;
    }

    public void setDishIds(List<DishId> dishIds) {
        this.dishIds = dishIds;
    }

}
