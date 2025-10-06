package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;
import be.kdg.keepdishesgoing.restaurant.port.in.UpdateDishVersionCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.UpdateDishVersionUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.UpdateDishVersionPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UpdateDishVersionUseCaseImpl implements UpdateDishVersionUseCase {

    private final UpdateDishVersionPort updateDishVersionPort;

    public UpdateDishVersionUseCaseImpl(UpdateDishVersionPort updateDishVersionPort) {
        this.updateDishVersionPort = updateDishVersionPort;
    }

    @Override
    @Transactional
    public DishVersion update(UpdateDishVersionCommand command) {
        return updateDishVersionPort.update(command.dishVersion());
    }
}
