package be.kdg.keepdishesgoing.customerOrder.port.out;

import be.kdg.keepdishesgoing.customerOrder.domain.BasketId;

public interface DeleteBasketPort {
    void delete(BasketId basketId);
}
