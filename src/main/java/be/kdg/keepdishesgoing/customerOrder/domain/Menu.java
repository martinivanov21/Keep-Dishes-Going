package be.kdg.keepdishesgoing.customerOrder.domain;


public class Menu {
    private MenuId menuId;
    private RestaurantId restaurantId;

    public Menu(MenuId menuId, RestaurantId restaurantId) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
    }

    public MenuId getMenuId() {
        return menuId;
    }

    public void setMenuId(MenuId menuId) {
        this.menuId = menuId;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }
}
