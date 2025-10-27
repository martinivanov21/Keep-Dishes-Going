package be.kdg.keepdishesgoing.customerOrder.port.out;

import be.kdg.keepdishesgoing.customerOrder.domain.Basket;
import be.kdg.keepdishesgoing.customerOrder.domain.BasketId;

import java.util.Optional;

public interface LoadBasketPort {
    Optional<Basket> loadById(BasketId basketId);
}
