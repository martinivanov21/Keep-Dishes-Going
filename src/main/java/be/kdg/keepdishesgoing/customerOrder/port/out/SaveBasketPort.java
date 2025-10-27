package be.kdg.keepdishesgoing.customerOrder.port.out;

import be.kdg.keepdishesgoing.customerOrder.domain.Basket;

public interface SaveBasketPort {
    Basket save(Basket basket);
}
