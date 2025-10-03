package be.kdg.keepdishesgoing.restaurant.adapter.out.dish;

import be.kdg.keepdishesgoing.restaurant.domain.enums.DishType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "dish_versions", schema = "kdg_restaurant")
public class DishVersionJpaEntity {

    @Id
    @Column(name = "dish_version_id", nullable = false)
    private UUID dishVersionId;

    @Column(nullable = false)
    private String nameOfDish;

    private String description;

    private String picture;

    @Column(nullable = false)
    private double price;

    private LocalDateTime preparationTime;

    @Enumerated(EnumType.STRING)
    private DishType dishType;
}
