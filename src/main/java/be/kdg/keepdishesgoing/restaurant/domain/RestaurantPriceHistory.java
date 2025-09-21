package be.kdg.keepdishesgoing.restaurant.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class RestaurantPriceHistory {
    private UUID priceHistoryId;
    private double averagePrice;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;

    private Restaurant restaurant;

    public RestaurantPriceHistory() {}

    public RestaurantPriceHistory(LocalDateTime validUntil, LocalDateTime validFrom, double averagePrice) {
        this.validUntil = validUntil;
        this.validFrom = validFrom;
        this.averagePrice = averagePrice;
    }
}
