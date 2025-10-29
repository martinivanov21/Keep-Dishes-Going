package be.kdg.keepdishesgoing.customerOrder.core;

import be.kdg.keepdishesgoing.customerOrder.domain.Basket;
import be.kdg.keepdishesgoing.customerOrder.domain.BasketId;
import be.kdg.keepdishesgoing.customerOrder.domain.DishId;
import be.kdg.keepdishesgoing.customerOrder.port.in.RemoveFromBasketCommand;
import be.kdg.keepdishesgoing.customerOrder.port.in.RemoveFromBasketUseCase;
import be.kdg.keepdishesgoing.customerOrder.port.in.RemoveItemFromBasketCommand;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadBasketPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveBasketPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RemoveFromBasketUseCaseImpl implements RemoveFromBasketUseCase {

    private final LoadBasketPort loadBasketPort;
    private final SaveBasketPort saveBasketPort;

    public RemoveFromBasketUseCaseImpl(LoadBasketPort loadBasketPort, SaveBasketPort saveBasketPort) {
        this.loadBasketPort = loadBasketPort;
        this.saveBasketPort = saveBasketPort;
    }

    @Override
    @Transactional
    public Basket removeFromBasket(RemoveFromBasketCommand command) {
        Basket basket = loadBasketPort.loadById(new BasketId(command.basketId()))
                .orElseThrow(() -> new IllegalArgumentException("Basket not found"));

        basket.removeItem(new DishId(command.dishId()));

        return saveBasketPort.save(basket);
    }
}
