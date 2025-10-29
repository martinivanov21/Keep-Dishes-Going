package be.kdg.keepdishesgoing.customerOrder.core;

import be.kdg.keepdishesgoing.customerOrder.domain.Basket;
import be.kdg.keepdishesgoing.customerOrder.domain.BasketId;
import be.kdg.keepdishesgoing.customerOrder.port.in.ClearBasketCommand;
import be.kdg.keepdishesgoing.customerOrder.port.in.ClearBasketUseCase;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadBasketPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveBasketPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ClearBasketUseCaseImpl implements ClearBasketUseCase {

    private final LoadBasketPort loadBasketPort;
    private final SaveBasketPort saveBasketPort;

    public ClearBasketUseCaseImpl(LoadBasketPort loadBasketPort, SaveBasketPort saveBasketPort) {
        this.loadBasketPort = loadBasketPort;
        this.saveBasketPort = saveBasketPort;
    }

    @Override
    @Transactional
    public void clearBasket(ClearBasketCommand command) {
        Basket basket = loadBasketPort.loadById(new BasketId(command.basketId()))
                .orElseThrow(() -> new IllegalArgumentException("Basket not found"));

        basket.clear();
        saveBasketPort.save(basket);

    }
}
