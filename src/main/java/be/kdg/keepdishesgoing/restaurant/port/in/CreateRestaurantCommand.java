package be.kdg.keepdishesgoing.restaurant.port.in;


import be.kdg.keepdishesgoing.restaurant.domain.OwnerId;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;

public record CreateRestaurantCommand(Restaurant restaurant)  {
}
