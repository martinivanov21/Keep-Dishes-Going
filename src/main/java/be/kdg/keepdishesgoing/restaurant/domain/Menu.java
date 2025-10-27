package be.kdg.keepdishesgoing.restaurant.domain;

import java.util.List;

public class Menu {

    private MenuId menuId;

    private RestaurantId restaurantId;

    private List<DishId> dishIds;


    public Menu(MenuId menuId, RestaurantId restaurantId, List<DishId> dishIds) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.dishIds = dishIds;
    }

    public Menu(MenuId menuId, List<DishId> dishIds) {
        this.menuId = menuId;
        this.dishIds = dishIds;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
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
