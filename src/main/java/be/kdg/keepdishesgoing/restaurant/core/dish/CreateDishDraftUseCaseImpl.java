package be.kdg.keepdishesgoing.restaurant.core.dish;

import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.domain.enums.DishStatus;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.CreateDishDraftCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.CreateDishDraftUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.SaveDishPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateDishDraftUseCaseImpl implements CreateDishDraftUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateDishDraftUseCaseImpl.class);
    private final List<SaveDishPort> saveDishPorts;

    public CreateDishDraftUseCaseImpl(List<SaveDishPort> saveDishPorts) {
        this.saveDishPorts = saveDishPorts;
    }


    @Override
    @Transactional
    public Dish createDraft(CreateDishDraftCommand command) {
        Dish dish = command.dish();

        logger.info("Creating draft for Dish {}", dish.getDraftVersion().getNameOfDish());

        if (dish.getStatus() != DishStatus.UNPUBLISHED) {
            throw new IllegalStateException("New dishes must be created as UNPUBLISHED (draft)");
        }

        if (dish.getLiveVersion() != null) {
            throw new IllegalArgumentException("New dish cannot have a live version");
        }

        if (dish.getDraftVersion() == null) {
            throw new IllegalArgumentException("Draft version is required");
        }

        for (SaveDishPort saveDishPort : saveDishPorts) {
            dish = saveDishPort.saveDish(dish);
        }

        logger.info("Draft {} created", dish.getDraftVersion().getNameOfDish());
        return dish;
    }
}
