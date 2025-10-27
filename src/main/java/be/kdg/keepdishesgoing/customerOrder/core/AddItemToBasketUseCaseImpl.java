package be.kdg.keepdishesgoing.customerOrder.core;

import be.kdg.keepdishesgoing.customerOrder.domain.Basket;
import be.kdg.keepdishesgoing.customerOrder.domain.Dish;
import be.kdg.keepdishesgoing.customerOrder.domain.DishId;
import be.kdg.keepdishesgoing.customerOrder.domain.DishStatus;
import be.kdg.keepdishesgoing.customerOrder.port.in.AddItemToBasketCommand;
import be.kdg.keepdishesgoing.customerOrder.port.in.AddItemToBasketUseCase;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadBasketPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadDishPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveBasketPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AddItemToBasketUseCaseImpl implements AddItemToBasketUseCase {

    private final LoadBasketPort loadBasketPort;
    private final SaveBasketPort saveBasketPort;
    private final LoadDishPort loadDishPort;

    public AddItemToBasketUseCaseImpl(LoadBasketPort loadBasketPort, SaveBasketPort saveBasketPort, LoadDishPort loadDishPort) {
        this.loadBasketPort = loadBasketPort;
        this.saveBasketPort = saveBasketPort;
        this.loadDishPort = loadDishPort;
    }

    @Override
    @Transactional
    public Basket addItem(AddItemToBasketCommand command) {
        Dish dish = loadDishPort.loadById(DishId.of(command.dishId())).orElseThrow();

        if (dish.getStatus() != DishStatus.PUBLISHED) {
            throw new IllegalStateException("Dish is not published");
        }
        return null;


    }

}
