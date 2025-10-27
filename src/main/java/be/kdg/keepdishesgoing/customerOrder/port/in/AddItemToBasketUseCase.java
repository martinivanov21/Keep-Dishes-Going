package be.kdg.keepdishesgoing.customerOrder.port.in;

import be.kdg.keepdishesgoing.customerOrder.domain.Basket;

public interface AddItemToBasketUseCase {
    Basket addItem(AddItemToBasketCommand command);
}
