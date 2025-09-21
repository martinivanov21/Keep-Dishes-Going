package be.kdg.keepdishesgoing.restaurant.domain.enums;

public enum FoodTag {
    LACTOSE("lactose"), GLUTEN("gluten"), VEGAN("vegan");

    private String value;

    private FoodTag(String value) {
        this.value = value;
    }

}
