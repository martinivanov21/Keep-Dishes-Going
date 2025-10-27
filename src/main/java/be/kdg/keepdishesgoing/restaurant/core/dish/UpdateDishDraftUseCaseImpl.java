package be.kdg.keepdishesgoing.restaurant.core.dish;

import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.UpdateDishDraftCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.UpdateDishDraftUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.UpdateDishPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateDishDraftUseCaseImpl implements UpdateDishDraftUseCase {

    private final List<UpdateDishPort> updateDishPorts;

    public UpdateDishDraftUseCaseImpl(List<UpdateDishPort> updateDishPorts) {
        this.updateDishPorts = updateDishPorts;
    }

    @Override
    @Transactional
    public Dish updateDraft(UpdateDishDraftCommand command) {
        Dish dish = command.dish();
        updateDishPorts.forEach(updateDishPort -> {updateDishPort.updateDish(dish);});
        return dish;
    }
}
