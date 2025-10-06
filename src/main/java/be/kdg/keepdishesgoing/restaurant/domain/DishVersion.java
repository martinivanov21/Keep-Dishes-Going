package be.kdg.keepdishesgoing.restaurant.domain;

import be.kdg.keepdishesgoing.restaurant.domain.enums.DishType;
import be.kdg.keepdishesgoing.restaurant.domain.enums.FoodTag;

import java.time.LocalDateTime;
import java.util.UUID;

public class DishVersion {
    private DishVersionId dishVersionId;
    private String nameOfDish;
    private String description;
    private double price;
    private String picture;
    private String preparationTime;
    private FoodTag foodTag;
    private DishType typeOfDish;

    public DishVersion(DishVersionId dishVersionId, String nameOfDish, String description,
                       double price, String picture, String preparationTime, FoodTag foodTag, DishType typeOfDish) {
        this.dishVersionId = dishVersionId;
        this.nameOfDish = nameOfDish;
        this.description = description;
        this.price = price;
        this.picture = picture;
        this.preparationTime = preparationTime;
        this.foodTag = foodTag;
        this.typeOfDish = typeOfDish;
    }

    public DishVersionId getDishVersionId() {
        return dishVersionId;
    }

    public void setDishVersionId(DishVersionId dishVersionId) {
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

    public DishType getTypeOfDish() {
        return typeOfDish;
    }

    public void setTypeOfDish(DishType typeOfDish) {
        this.typeOfDish = typeOfDish;
    }
}
