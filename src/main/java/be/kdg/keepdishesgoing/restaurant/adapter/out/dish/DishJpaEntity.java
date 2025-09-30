package be.kdg.keepdishesgoing.restaurant.adapter.out.dish;

import be.kdg.keepdishesgoing.restaurant.adapter.out.menu.MenuJpaEntity;
import be.kdg.keepdishesgoing.restaurant.domain.enums.DishStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "dish")
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


}
