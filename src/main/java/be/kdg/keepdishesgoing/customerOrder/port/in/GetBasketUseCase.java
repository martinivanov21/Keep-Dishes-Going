package be.kdg.keepdishesgoing.customerOrder.port.in;

import be.kdg.keepdishesgoing.customerOrder.domain.Basket;

public interface GetBasketUseCase {
    Basket getBasket(GetBasketCommand command);
}
