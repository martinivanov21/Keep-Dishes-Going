package be.kdg.keepdishesgoing.customerOrder.core;

import be.kdg.keepdishesgoing.customerOrder.domain.Basket;
import be.kdg.keepdishesgoing.customerOrder.domain.BasketId;
import be.kdg.keepdishesgoing.customerOrder.domain.DishId;
import be.kdg.keepdishesgoing.customerOrder.port.in.UpdateBasketItemCommand;
import be.kdg.keepdishesgoing.customerOrder.port.in.UpdateBasketItemUseCase;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadBasketPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveBasketPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateBasketItemUseCaseImpl implements UpdateBasketItemUseCase {

    private final LoadBasketPort loadBasketPort;
    private final SaveBasketPort saveBasketPort;

    public UpdateBasketItemUseCaseImpl(LoadBasketPort loadBasketPort, SaveBasketPort saveBasketPort) {
        this.loadBasketPort = loadBasketPort;
        this.saveBasketPort = saveBasketPort;
    }


    @Override
    public Basket updateItem(UpdateBasketItemCommand command) {
        Basket basket = loadBasketPort.loadById(new BasketId(command.basketId()))
                .orElseThrow(() -> new IllegalArgumentException("Basket not found"));

        basket.updateItemQuantity(new DishId(command.request().dishId()), command.request().quantity());

        return saveBasketPort.save(basket);
    }


}
