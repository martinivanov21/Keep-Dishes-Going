package be.kdg.keepdishesgoing.restaurant.adapter.in.request;

import be.kdg.keepdishesgoing.restaurant.adapter.in.response.DishDto;

import java.util.List;

public record CreateMenuRequest(
        List<DishDto> dishes
) {
}
