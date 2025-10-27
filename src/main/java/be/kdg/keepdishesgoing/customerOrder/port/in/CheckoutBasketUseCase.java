package be.kdg.keepdishesgoing.customerOrder.port.in;

import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrder;

public interface CheckoutBasketUseCase {
    CustomerOrder checkoutBasket(CheckoutBasketCommand command);
}
