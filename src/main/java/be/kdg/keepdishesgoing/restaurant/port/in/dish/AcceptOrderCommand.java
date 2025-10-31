package be.kdg.keepdishesgoing.restaurant.port.in.dish;

import java.util.UUID;

public record AcceptOrderCommand(UUID ownerId, UUID restaurantId, UUID orderId, int finalEstimatedTime) {}
