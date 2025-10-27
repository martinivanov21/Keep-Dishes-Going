package be.kdg.keepdishesgoing.customerOrder.port.in;

import be.kdg.keepdishesgoing.customerOrder.domain.Basket;

public interface RemoveItemFromBasketUseCase {
    Basket removeItem(RemoveItemFromBasketCommand command);
}
