package be.kdg.keepdishesgoing.restaurant.adapter.in.response;

import java.util.List;
import java.util.UUID;

public record MenuDto(
        UUID menuId,
        List<DishDto> dishes
) {
}
