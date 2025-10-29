package be.kdg.keepdishesgoing.customerOrder.adapter.in.response;

import java.math.BigDecimal;
import java.util.UUID;

public record BasketItemDto(UUID dishId,
                            String dishName,
                            BigDecimal unitPrice,
                            int quantity,
                            BigDecimal subtotal,
                            String pictureUrl,
                            boolean available) {
}
