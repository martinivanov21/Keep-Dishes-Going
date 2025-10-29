package be.kdg.keepdishesgoing.customerOrder.adapter.in.response;

import be.kdg.keepdishesgoing.customerOrder.domain.OrderItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record BasketDto(
        UUID basketId,
        UUID restaurantId,
        String restaurantName,
        List<BasketItemDto> items,
        BigDecimal totalPrice,
        int totalItems,
        String status,
        LocalDateTime updatedAt
) {
}
