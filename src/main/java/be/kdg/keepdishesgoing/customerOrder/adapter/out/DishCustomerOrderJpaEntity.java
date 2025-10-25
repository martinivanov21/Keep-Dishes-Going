package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.domain.DishStatus;
import be.kdg.keepdishesgoing.customerOrder.domain.DishType;
import be.kdg.keepdishesgoing.customerOrder.domain.FoodTag;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "dish_customer_order", schema = "kdg_customer_order")
public class DishCustomerOrderJpaEntity {

    @Id
    @Column(name = "dish_id")
    private UUID dishId;

    @Column(nullable = false)
    private String nameOfDish;
    private String description;
    @Column(nullable = false)
    private double price;
    private String pictureUrl;
    private String preparationTime;
    @Enumerated(EnumType.STRING)
    private FoodTag foodTag;
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private DishStatus dishStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuCustomerOrderJpaEntity menu;

    public DishCustomerOrderJpaEntity() {
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DishStatus getDishStatus() {
        return dishStatus;
    }

    public void setDishStatus(DishStatus dishStatus) {
        this.dishStatus = dishStatus;
    }

    public UUID getDishId() {
        return dishId;
    }

    public void setDishId(UUID dishId) {
        this.dishId = dishId;
    }

    public MenuCustomerOrderJpaEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuCustomerOrderJpaEntity menu) {
        this.menu = menu;
    }
}
