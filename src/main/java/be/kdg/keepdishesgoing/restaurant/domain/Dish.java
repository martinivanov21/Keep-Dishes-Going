package be.kdg.keepdishesgoing.restaurant.domain;

import be.kdg.keepdishesgoing.restaurant.domain.enums.DishStatus;
import be.kdg.keepdishesgoing.restaurant.domain.enums.DishType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Dish {

    private UUID dishId;
    private DishVersion liveVersion;
    private DishVersion draftVersion;
    private DishStatus status;
    private int quantity;

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

    public Optional<Double> getCurrentPrice() {
        return liveVersion != null ? Optional.of(liveVersion.getPrice()) : Optional.empty();
    }


}
