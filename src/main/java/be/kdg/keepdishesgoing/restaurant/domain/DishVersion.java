package be.kdg.keepdishesgoing.restaurant.domain;

import be.kdg.keepdishesgoing.restaurant.domain.enums.DishType;
import be.kdg.keepdishesgoing.restaurant.domain.enums.FoodTag;

import java.time.LocalDateTime;
import java.util.UUID;

public class DishVersion {
    private UUID dishVersionId;
    private String nameOfDish;
    private String description;
    private double price;
    private String picture;
    private LocalDateTime preparationTime;
    private FoodTag foodTag;
    private DishType typeOfDish;

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

    public LocalDateTime getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(LocalDateTime preparationTime) {
        this.preparationTime = preparationTime;
    }

    public FoodTag getFoodTag() {
        return foodTag;
    }

    public void setFoodTag(FoodTag foodTag) {
        this.foodTag = foodTag;
    }

    public DishType getTypeOfDish() {
        return typeOfDish;
    }

    public void setTypeOfDish(DishType typeOfDish) {
        this.typeOfDish = typeOfDish;
    }
}
