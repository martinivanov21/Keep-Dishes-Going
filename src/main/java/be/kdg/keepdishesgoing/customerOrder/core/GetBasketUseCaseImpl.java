package be.kdg.keepdishesgoing.customerOrder.core;

import be.kdg.keepdishesgoing.customerOrder.domain.Basket;
import be.kdg.keepdishesgoing.customerOrder.domain.BasketId;
import be.kdg.keepdishesgoing.customerOrder.port.in.GetBasketCommand;
import be.kdg.keepdishesgoing.customerOrder.port.in.GetBasketUseCase;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadBasketPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadDishPort;
import org.springframework.stereotype.Service;

@Service
public class GetBasketUseCaseImpl implements GetBasketUseCase {
    private final LoadBasketPort loadBasketPort;

    public GetBasketUseCaseImpl(LoadBasketPort loadBasketPort) {
        this.loadBasketPort = loadBasketPort;
    }

    @Override
    public Basket getBasket(GetBasketCommand command) {
        return loadBasketPort.loadById(new BasketId(command.basketId()))
                .orElse(new Basket(new BasketId(command.basketId())));

    }
}
