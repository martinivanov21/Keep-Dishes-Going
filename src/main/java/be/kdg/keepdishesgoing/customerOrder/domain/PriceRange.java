package be.kdg.keepdishesgoing.customerOrder.domain;

public enum PriceRange {
    CHEAP("€"), REGULAR("€€"), EXPENSIVE("€€€"), PREMIUM("€€€€");

    private String value;

    private PriceRange(String value) {
        this.value = value;
    }
}
