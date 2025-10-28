package be.kdg.keepdishesgoing.restaurant.domain;

import be.kdg.keepdishesgoing.common.events.DomainEvent;
import be.kdg.keepdishesgoing.common.events.MenuCreatedEvent;
import be.kdg.keepdishesgoing.common.events.MenuUpdatedEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    private MenuId menuId;

    private RestaurantId restaurantId;

    private List<DishId> dishIds;

    private final List<DomainEvent> uncommittedEvents = new ArrayList<>();


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


    public void create() {
        if (this.restaurantId == null) {
            throw new IllegalStateException("Menu must have a restaurantId");
        }
        this.raiseCreatedEvent();
    }

    public void addDish(DishId dishId) {
        if (dishIds == null) {
            dishIds = new ArrayList<>();
        }
        if (!dishIds.contains(dishId)) {
            dishIds.add(dishId);
            this.raiseUpdatedEvent();
        }
    }

    public void removeDish(DishId dishId) {
        if (dishIds != null && dishIds.remove(dishId)) {
            this.raiseUpdatedEvent();
        }
    }

    private void raiseCreatedEvent() {
        MenuCreatedEvent event = new MenuCreatedEvent(
                this.menuId.uuid(),
                this.restaurantId.uuid(),
                LocalDateTime.now()
        );
        this.uncommittedEvents.add(event);
    }

    private void raiseUpdatedEvent() {
        MenuUpdatedEvent event = new MenuUpdatedEvent(
                this.menuId.uuid(),
                this.restaurantId.uuid(),
                LocalDateTime.now()
        );
        this.uncommittedEvents.add(event);
    }

    public List<DomainEvent> getUncommittedEvents() {
        return uncommittedEvents;
    }

    public void clearEvents() {
        uncommittedEvents.clear();
    }



}
