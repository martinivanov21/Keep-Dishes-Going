package be.kdg.keepdishesgoing.customerOrder.core;

import be.kdg.keepdishesgoing.customerOrder.domain.*;
import be.kdg.keepdishesgoing.customerOrder.port.in.AddItemToBasketCommand;
import be.kdg.keepdishesgoing.customerOrder.port.in.AddItemToBasketUseCase;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadBasketPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadDishPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadMenuPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveBasketPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AddItemToBasketUseCaseImpl implements AddItemToBasketUseCase {

    private final LoadBasketPort loadBasketPort;
    private final SaveBasketPort saveBasketPort;
    private final LoadDishPort loadDishPort;
    private final LoadMenuPort loadMenuPort;

    public AddItemToBasketUseCaseImpl(LoadBasketPort loadBasketPort, SaveBasketPort saveBasketPort, LoadDishPort loadDishPort, LoadMenuPort loadMenuPort) {
        this.loadBasketPort = loadBasketPort;
        this.saveBasketPort = saveBasketPort;
        this.loadDishPort = loadDishPort;
        this.loadMenuPort = loadMenuPort;
    }

    @Override
    @Transactional
    public Basket addToBasket(AddItemToBasketCommand command) {
        // Load or create basket
        Basket basket = loadBasketPort.loadById(new BasketId(command.basketId()))
                .orElse(new Basket(new BasketId(command.basketId())));

        // Load dish and validate
        Dish dish = loadDishPort.loadById(new DishId(command.request().dishId()))
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        if (dish.getStatus() != DishStatus.PUBLISHED) {
            throw new IllegalStateException("Dish is not available");
        }

        if (dish.getQuantity() < command.request().quantity()) {
            throw new IllegalStateException("Insufficient stock for this dish");
        }

        // Get restaurant ID from dish's menu
        RestaurantId restaurantId = getRestaurantIdFromDish(dish);

        // Add to basket
        basket.addItem(
                dish.getDishId(),
                dish.getNameOfDish(),
                dish.getPrice(),
                dish.getPictureUrl(),
                command.request().quantity(),
                restaurantId
        );

        return saveBasketPort.save(basket);
    }

    private RestaurantId getRestaurantIdFromDish(Dish dish) {
        Menu menu = loadMenuPort.loadById(dish.getMenuId());
        return menu.getRestaurantId();
    }

}
