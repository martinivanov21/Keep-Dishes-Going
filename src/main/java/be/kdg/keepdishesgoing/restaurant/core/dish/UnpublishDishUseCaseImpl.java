package be.kdg.keepdishesgoing.restaurant.core.dish;

import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.UnpublishDishCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.UnpublishDishUseCase;
import org.springframework.stereotype.Service;

@Service
public class UnpublishDishUseCaseImpl implements UnpublishDishUseCase {
    @Override
    public Dish unpublishDish(UnpublishDishCommand unpublishDishCommand) {
        return null;
    }
}
