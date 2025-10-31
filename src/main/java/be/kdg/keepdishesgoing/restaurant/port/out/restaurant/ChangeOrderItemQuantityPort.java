package be.kdg.keepdishesgoing.restaurant.port.out.restaurant;

import be.kdg.keepdishesgoing.restaurant.port.in.dish.ChangeItemQuantityCommand;

public interface ChangeOrderItemQuantityPort {
    void changeQuantity(ChangeItemQuantityCommand cmd);
}