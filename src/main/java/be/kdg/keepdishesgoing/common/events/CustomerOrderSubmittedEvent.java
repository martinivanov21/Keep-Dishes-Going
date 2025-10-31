package be.kdg.keepdishesgoing.common.events;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.OrderItemDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CustomerOrderSubmittedEvent(
        UUID customerOrderId,
        UUID restaurantId,
        String customerName,
        String customerEmail,
        String deliveryStreet,
        int deliveryNumber,
        String deliveryCity,
        BigDecimal totalPrice,
        int estimatedTime,
        LocalDateTime submittedTime,
        List<OrderItemDto> items,
        LocalDateTime occurredOn
) implements DomainEvent {
    @Override
    public LocalDateTime eventPit() {
        return null;
    }
}
