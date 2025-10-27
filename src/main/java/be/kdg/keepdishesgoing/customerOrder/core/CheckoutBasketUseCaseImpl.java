package be.kdg.keepdishesgoing.customerOrder.core;

import be.kdg.keepdishesgoing.customerOrder.domain.*;
import be.kdg.keepdishesgoing.customerOrder.port.in.CheckoutBasketCommand;
import be.kdg.keepdishesgoing.customerOrder.port.in.CheckoutBasketUseCase;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadBasketPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadRestaurantPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveBasketPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveCustomerOrderPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CheckoutBasketUseCaseImpl implements CheckoutBasketUseCase {

    private final LoadBasketPort loadBasketPort;
    private final SaveBasketPort saveBasketPort;
    private final SaveCustomerOrderPort saveCustomerOrderPort;
    private final LoadRestaurantPort loadRestaurantPort;

    public CheckoutBasketUseCaseImpl(LoadBasketPort loadBasketPort, SaveBasketPort saveBasketPort,
                                     SaveCustomerOrderPort saveCustomerOrderPort, LoadRestaurantPort loadRestaurantPort) {
        this.loadBasketPort = loadBasketPort;
        this.saveBasketPort = saveBasketPort;
        this.saveCustomerOrderPort = saveCustomerOrderPort;
        this.loadRestaurantPort = loadRestaurantPort;
    }


    @Override
    @Transactional
    public CustomerOrder checkoutBasket(CheckoutBasketCommand command) {

        Basket basket = loadBasketPort.loadById(BasketId.of(command.basketId().toString()))
                .orElseThrow(() -> new IllegalArgumentException("Basket not found"));

        if (!basket.canCheckout()) {
            throw new IllegalStateException("Basket is empty or not ready for checkout");
        }

        Restaurant restaurant = loadRestaurantPort.loadById(basket.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        double estimatedTime = restaurant.getGuesstimatedDeliveryTimeMinutes();

        Address deliveryAddress = new Address(command.street(), command.number(),command.city());

//        CustomerOrder order = basket.c
        CustomerOrder savedOrder = saveCustomerOrderPort.save(order);

        saveBasketPort.save(basket);
        return savedOrder;


    }
}
