package be.kdg.keepdishesgoing.restaurant.domain.enums;

public enum DishStatus {
    PUBLISHED("published"), UNPUBLISHED("unpublished"), OUT_OF_STOCK("out of stock");

    private String value;

    private DishStatus(String value) {
        this.value = value;
    }
}
