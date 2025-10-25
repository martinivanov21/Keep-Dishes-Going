package be.kdg.keepdishesgoing.customerOrder.domain;

public enum DishType {
    STARTER("starter"),MAIN("main"),DESERT("desert");

    private String value;

    private DishType(String value) {
        this.value = value;
    }
}
