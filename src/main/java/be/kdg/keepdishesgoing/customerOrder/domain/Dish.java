package be.kdg.keepdishesgoing.customerOrder.domain;



public class Dish {
    private DishId dishId;
    private MenuId menuId;
    private String nameOfDish;
    private String description;
    private double price;
    private String pictureUrl;
    private String preparationTime;
    private FoodTag foodTag;
    private DishType dishType;
    private DishStatus status;
    private int quantity;

    public Dish(DishId dishId, MenuId menuId, String nameOfDish, String description, double price, String pictureUrl,
                String preparationTime, FoodTag foodTag, DishType dishType, DishStatus status, int quantity) {
        this.dishId = dishId;
        this.menuId = menuId;
        this.nameOfDish = nameOfDish;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.preparationTime = preparationTime;
        this.foodTag = foodTag;
        this.dishType = dishType;
        this.status = status;
        this.quantity = quantity;
    }

    public DishId getDishId() {
        return dishId;
    }

    public void setDishId(DishId dishId) {
        this.dishId = dishId;
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

    public MenuId getMenuId() {
        return menuId;
    }

    public void setMenuId(MenuId menuId) {
        this.menuId = menuId;
    }
}
