package be.kdg.keepdishesgoing.restaurant.domain;

import be.kdg.keepdishesgoing.common.events.DishUpdatedEvent;
import be.kdg.keepdishesgoing.common.events.DomainEvent;
import be.kdg.keepdishesgoing.restaurant.domain.enums.DishStatus;
import be.kdg.keepdishesgoing.restaurant.domain.enums.DishType;

import java.time.LocalDateTime;
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

    private final List<DomainEvent> uncommittedEvents = new ArrayList<>();

    public Dish(DishId dishId, DishVersion liveVersion, DishVersion draftVersion,
                DishStatus status, int quantity, Menu menu) {
        this.dishId = dishId;
        this.liveVersion = liveVersion;
        this.draftVersion = draftVersion;
        this.status = status;
        this.quantity = quantity;
        this.menu = menu;
    }

    public Dish(DishId dishId, DishVersion draftVersion, int quantity, Menu menu) {
        this.dishId = dishId;
        this.draftVersion = draftVersion;
        this.liveVersion = null;
        this.status = DishStatus.UNPUBLISHED;
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
        if (this.draftVersion == null) {
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

    public void clearEvents() {
        this.uncommittedEvents.clear();
    }

    public List<DomainEvent> getUncommittedEvents() {
        return uncommittedEvents;
    }

    public List<Object> getDomainEvents() {
        return domainEvents;
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

//    private void raiseUpdatedEvent() {
//        DishUpdatedEvent event = new DishUpdatedEvent(
//                this.dishId.uuid(),
//                RestaurantId.of()
//                this.liveVersion.getNameOfDish(),
//                this.liveVersion.getDescription(),
//                this.liveVersion.getPrice(),
//                this.liveVersion.getPicture(),
//                this.liveVersion.getPreparationTime(),
//                this.liveVersion.getFoodTag().name(),
//                this.liveVersion.getTypeOfDish().name(),
//                this.quantity,
//                LocalDateTime.now()
//        );
//
//        this.uncommittedEvents.add(event);
//    }

    public boolean isAvailable() {
        return status == DishStatus.PUBLISHED && quantity > 0;
    }


    public Optional<Double> getCurrentPrice() {
        return liveVersion != null ? Optional.of(liveVersion.getPrice()) : Optional.empty();
    }


}
