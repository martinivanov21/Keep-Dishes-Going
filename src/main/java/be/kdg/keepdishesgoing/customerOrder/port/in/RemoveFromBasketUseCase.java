package be.kdg.keepdishesgoing.customerOrder.port.in;

import be.kdg.keepdishesgoing.customerOrder.domain.Basket;

public interface RemoveFromBasketUseCase {
    Basket removeFromBasket(RemoveFromBasketCommand command);
}
