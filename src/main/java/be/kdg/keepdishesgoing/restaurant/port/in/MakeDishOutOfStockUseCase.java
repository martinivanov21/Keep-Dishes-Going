package be.kdg.keepdishesgoing.restaurant.port.in;

import be.kdg.keepdishesgoing.restaurant.domain.exceptions.UnauthorizedOwnerException;
import org.springframework.web.client.HttpClientErrorException;

public interface MakeDishOutOfStockUseCase {

    void makeDishOutOfStock(MakeDishOutOfStockCommand command) throws UnauthorizedOwnerException;
}
