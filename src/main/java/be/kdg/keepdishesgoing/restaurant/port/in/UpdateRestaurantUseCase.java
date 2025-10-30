package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;

public interface UpdateRestaurantUseCase {
    Restaurant update(UpdateRestaurantCommand command);
}
