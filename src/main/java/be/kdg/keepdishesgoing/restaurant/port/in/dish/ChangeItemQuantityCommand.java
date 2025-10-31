package be.kdg.keepdishesgoing.restaurant.port.in.dish;

import java.util.UUID;

public record ChangeItemQuantityCommand(UUID ownerId, UUID restaurantId, UUID orderId, UUID dishId, int quantity) {}

