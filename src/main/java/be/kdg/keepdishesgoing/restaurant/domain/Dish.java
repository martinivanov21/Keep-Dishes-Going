package be.kdg.keepdishesgoing.restaurant.domain;

import be.kdg.keepdishesgoing.restaurant.domain.enums.DishStatus;
import be.kdg.keepdishesgoing.restaurant.domain.enums.DishType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Dish {

    private DishId dishId;
    private DishVersion liveVersion;
    private DishVersion draftVersion;
    private DishStatus status;
    private int quantity;

    private Menu menu;

    public Dish(DishId dishId, DishVersion liveVersion, DishVersion draftVersion,
                DishStatus status, int quantity, Menu menu) {
        this.dishId = dishId;
        this.liveVersion = liveVersion;
        this.draftVersion = draftVersion;
        this.status = status;
        this.quantity = quantity;
        this.menu = menu;
    }

    public DishId getDishId() {
        return dishId;
    }

    public void setDishId(DishId dishId) {
        this.dishId = dishId;
    }

    private final List<Object> domainEvents = new ArrayList<>();

    public DishVersion getLiveVersion() {
        return liveVersion;
    }

    public void setLiveVersion(DishVersion liveVersion) {
        this.liveVersion = liveVersion;
    }

    public DishVersion getDraftVersion() {
        return draftVersion;
    }

    public void setDraftVersion(DishVersion draftVersion) {
        this.draftVersion = draftVersion;
    }

    public DishStatus getStatus() {
        return status;
    }

    public void setStatus(DishStatus status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }


    public void createDraft(DishVersion draftVersion) {
        if (status == DishStatus.UNPUBLISHED) {
            throw new IllegalStateException("Draft already created");
        }
        this.draftVersion = draftVersion;
        this.status = DishStatus.UNPUBLISHED;
    }

    public void publish() {
        if (draftVersion == null) {
            throw new IllegalStateException("Draft version is null");
        }
        this.liveVersion = draftVersion;
        this.draftVersion = null;
        this.status = DishStatus.PUBLISHED;
    }

    public void unpublish() {
        this.liveVersion = null;
        this.status = DishStatus.UNPUBLISHED;
    }

    public void markOutOfStock() {
        if (status != DishStatus.PUBLISHED) {
            throw new IllegalStateException("Only published dishes can be marked out of stock");
        }
        this.status = DishStatus.OUT_OF_STOCK;
    }

    public void markBackInStock() {
        if (status != DishStatus.OUT_OF_STOCK) {
            throw new IllegalStateException("Dish is not currently out of stock");
        }
        this.status = DishStatus.PUBLISHED;
    }

    public boolean isAvailable() {
        return status == DishStatus.PUBLISHED && quantity > 0;
    }

    public Optional<Double> getCurrentPrice() {
        return liveVersion != null ? Optional.of(liveVersion.getPrice()) : Optional.empty();
    }


}
