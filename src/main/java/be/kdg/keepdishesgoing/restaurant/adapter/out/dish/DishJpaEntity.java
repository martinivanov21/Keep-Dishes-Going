package be.kdg.keepdishesgoing.restaurant.adapter.out.dish;

import be.kdg.keepdishesgoing.restaurant.adapter.out.menu.MenuJpaEntity;
import be.kdg.keepdishesgoing.restaurant.domain.enums.DishStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "dish", schema = "kdg_restaurant")
public class DishJpaEntity {

    @Id
    @Column(name = "dish_id", updatable = false, nullable = false)
    private UUID dishId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "live_version_id")
    private DishVersionJpaEntity liveVersion;

    @OneToOne(cascade =  CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "draft_version_id")
    private DishVersionJpaEntity draftVersion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DishStatus status;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuJpaEntity menu;

    public DishJpaEntity() {
    }

    public DishJpaEntity(UUID dishId, DishVersionJpaEntity liveVersion, DishVersionJpaEntity draftVersion,
                         DishStatus status, int quantity, MenuJpaEntity menu) {
        this.dishId = dishId;
        this.liveVersion = liveVersion;
        this.draftVersion = draftVersion;
        this.status = status;
        this.quantity = quantity;
        this.menu = menu;
    }

    public UUID getDishId() {
        return dishId;
    }

    public void setDishId(UUID dishId) {
        this.dishId = dishId;
    }

    public DishVersionJpaEntity getLiveVersion() {
        return liveVersion;
    }

    public void setLiveVersion(DishVersionJpaEntity liveVersion) {
        this.liveVersion = liveVersion;
    }

    public DishVersionJpaEntity getDraftVersion() {
        return draftVersion;
    }

    public void setDraftVersion(DishVersionJpaEntity draftVersion) {
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

    public MenuJpaEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuJpaEntity menu) {
        this.menu = menu;
    }

}
