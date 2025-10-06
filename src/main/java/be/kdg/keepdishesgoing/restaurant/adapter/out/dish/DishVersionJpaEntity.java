package be.kdg.keepdishesgoing.restaurant.adapter.out.dish;

import be.kdg.keepdishesgoing.restaurant.domain.enums.DishType;
import be.kdg.keepdishesgoing.restaurant.domain.enums.FoodTag;
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

    @Column(nullable = false)
    private double price;

    private String picture;

    private String preparationTime;

    @Enumerated(EnumType.STRING)
    private FoodTag foodTag;
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    public DishVersionJpaEntity(UUID dishVersionId, String nameOfDish,
                                String description, double price, String picture, String preparationTime, FoodTag foodTag, DishType dishType) {
        this.dishVersionId = dishVersionId;
        this.nameOfDish = nameOfDish;
        this.description = description;
        this.price = price;
        this.picture = picture;
        this.preparationTime = preparationTime;
        this.foodTag = foodTag;
        this.dishType = dishType;
    }

    public DishVersionJpaEntity() {
    }

    public UUID getDishVersionId() {
        return dishVersionId;
    }

    public void setDishVersionId(UUID dishVersionId) {
        this.dishVersionId = dishVersionId;
    }

    public String getNameOfDish() {
        return nameOfDish;
    }

    public void setNameOfDish(String nameOfDish) {
        this.nameOfDish = nameOfDish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public FoodTag getFoodTag() {
        return foodTag;
    }

    public void setFoodTag(FoodTag foodTag) {
        this.foodTag = foodTag;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }
}
