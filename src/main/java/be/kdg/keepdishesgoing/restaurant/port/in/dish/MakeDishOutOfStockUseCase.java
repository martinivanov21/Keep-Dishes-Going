package be.kdg.keepdishesgoing.restaurant.port.in.dish;

import be.kdg.keepdishesgoing.restaurant.domain.exceptions.UnauthorizedOwnerException;

public interface MakeDishOutOfStockUseCase {

    void makeDishOutOfStock(MakeDishOutOfStockCommand command) throws UnauthorizedOwnerException;
}
