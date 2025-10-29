package be.kdg.keepdishesgoing.customerOrder.core;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.CheckoutRequest;
import be.kdg.keepdishesgoing.customerOrder.domain.*;
import be.kdg.keepdishesgoing.customerOrder.port.in.CheckoutBasketCommand;
import be.kdg.keepdishesgoing.customerOrder.port.in.CheckoutBasketUseCase;
import be.kdg.keepdishesgoing.customerOrder.port.out.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CheckoutBasketUseCaseImpl implements CheckoutBasketUseCase {

    private final LoadBasketPort loadBasketPort;
    private final SaveBasketPort saveBasketPort;
    private final SaveCustomerOrderPort saveOrderPort;
    private final LoadDishPort loadDishPort;
    private final LoadRestaurantPort loadRestaurantPort;

    public CheckoutBasketUseCaseImpl(LoadBasketPort loadBasketPort, SaveBasketPort saveBasketPort, SaveCustomerOrderPort saveOrderPort, LoadDishPort loadDishPort,
                                     LoadRestaurantPort loadRestaurantPort) {
        this.loadBasketPort = loadBasketPort;
        this.saveBasketPort = saveBasketPort;
        this.saveOrderPort = saveOrderPort;
        this.loadDishPort = loadDishPort;
        this.loadRestaurantPort = loadRestaurantPort;
    }

        private void validateDishesAvailability(Basket basket) {
        List<String> unavailableDishes = new ArrayList<>();

        for (OrderItem item : basket.getItems()) {
            Dish dish = loadDishPort.loadById(item.getDishId())
                    .orElseThrow(() -> new IllegalStateException(
                            "Dish not found: " + item.getDishName()));

            if (dish.getStatus() != DishStatus.PUBLISHED) {
                unavailableDishes.add(item.getDishName() + " (unpublished)");
            } else if (dish.getQuantity() < item.getQuantity()) {
                unavailableDishes.add(item.getDishName() + " (insufficient stock)");
            }
        }

        if (!unavailableDishes.isEmpty()) {
            throw new IllegalStateException(
                    "Some items in your basket are no longer available: " +
                            String.join(", ", unavailableDishes));
        }
    }

    @Override
    @Transactional
    public CustomerOrder checkout(CheckoutBasketCommand command) {
        // Load basket
        Basket basket = loadBasketPort.loadById(new BasketId(command.basketId()))
                .orElseThrow(() -> new IllegalArgumentException("Basket not found"));

        // Validate all dishes are still available
        validateDishesAvailability(basket);

        // Get restaurant delivery time
        Restaurant restaurant = loadRestaurantPort.loadById(basket.getRestaurantId())
                .orElseThrow(() -> new IllegalStateException("Restaurant not found"));

        int estimatedTime = restaurant.getGuesstimatedDeliveryTimeMinutes();

        // Checkout
        CustomerOrder order = basket.checkout(
                command.request().customerName(),
                command.request().customerEmail(),
                command.request().deliveryStreet(),
                command.request().deliveryNumber(),
                command.request().deliveryCity(),
                estimatedTime
        );

        // Save order and updated basket
        CustomerOrder savedOrder = saveOrderPort.save(order);
        saveBasketPort.save(basket);


        return savedOrder;
    }
}
