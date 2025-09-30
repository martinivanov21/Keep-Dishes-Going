package be.kdg.keepdishesgoing.restaurant.domain;

import java.util.List;

public class Menu {

    private MenuId menuId;

    private List<DishId> dishIds;

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

    public Menu(MenuId menuId, List<DishId> dishIds) {
        this.menuId = menuId;
        this.dishIds = dishIds;
    }
}
