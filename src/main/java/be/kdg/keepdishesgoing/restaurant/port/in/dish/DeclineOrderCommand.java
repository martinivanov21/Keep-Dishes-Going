package be.kdg.keepdishesgoing.restaurant.port.in.dish;

import java.util.UUID;

public record DeclineOrderCommand(UUID ownerId, UUID restaurantId, UUID orderId, String reason) {}

