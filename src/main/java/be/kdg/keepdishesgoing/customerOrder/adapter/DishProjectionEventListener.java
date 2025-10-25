package be.kdg.keepdishesgoing.customerOrder.adapter;

import be.kdg.keepdishesgoing.common.events.DishPublishedEvent;
import be.kdg.keepdishesgoing.common.events.DishUnpublishedEvent;
import be.kdg.keepdishesgoing.common.events.DishUpdatedEvent;
import be.kdg.keepdishesgoing.customerOrder.domain.*;
import be.kdg.keepdishesgoing.customerOrder.port.out.DeleteDishPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadDishPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveDishPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DishProjectionEventListener {

    private static final Logger log = LoggerFactory.getLogger(DishProjectionEventListener.class);

    private final SaveDishPort saveDishPort;
    private final DeleteDishPort deleteDishPort;
    private final LoadDishPort loadDishPort;

    public DishProjectionEventListener(SaveDishPort saveDishPort, DeleteDishPort deleteDishPort, LoadDishPort loadDishPort) {
        this.saveDishPort = saveDishPort;
        this.deleteDishPort = deleteDishPort;
        this.loadDishPort = loadDishPort;
    }

    @EventListener
    @Transactional
    public void on(DishPublishedEvent event) {
        log.info("========================================");
        log.info("Received DishPublishedEvent: {}", event.nameOfDish());
        log.info("Dish ID: {}, Restaurant ID: {}", event.dishId(), event.restaurantId());
        log.info("========================================");

        Dish dish = new Dish(
                DishId.of(event.dishId()),
                event.nameOfDish(),
                event.description(),
                event.price(),
                event.pictureUrl(),
                event.preparationTime(),
                FoodTag.valueOf(event.foodTag()),
                DishType.valueOf(event.dishType()),
                DishStatus.PUBLISHED,
                event.quantity(),
                null
        );

        saveDishPort.saveDish(dish);
        log.info("Dish projection created successfully: {}", event.nameOfDish());
    }

    @EventListener
    @Transactional
    public void on(DishUnpublishedEvent event) {
        log.info("Received DishUnpublishedEvent for dish: {}", event.dishId());

        deleteDishPort.deleteDish(DishId.of(event.dishId()));
        log.info("Dish unpublished successfully: {}", event.dishId());

    }

    @EventListener
    @Transactional
    public void on(DishUpdatedEvent event) {
        log.info("Received DishUpdatedEvent: {}", event.nameOfDish());

        Optional<Dish> existingDish = loadDishPort.loadById(DishId.of(event.dishId()));

        if (existingDish.isPresent()) {
            Dish updatedDish = new Dish(
                    DishId.of(event.dishId()),
                    event.nameOfDish(),
                    event.description(),
                    event.price(),
                    event.pictureUrl(),
                    event.preparationTime(),
                    FoodTag.valueOf(event.foodTag()),
                    DishType.valueOf(event.dishType()),
                    DishStatus.PUBLISHED,
                    event.quantity(),
                    existingDish.get().getMenu()
            );

            saveDishPort.saveDish(updatedDish);
            log.info("Dish projection updated: {}", event.nameOfDish());
        } else {
            log.warn("Dish {} not found for update - creating new projection", event.dishId());
            on(new DishPublishedEvent(
                    event.dishId(), event.restaurantId(), event.nameOfDish(),
                    event.description(), event.price(), event.pictureUrl(),
                    event.preparationTime(), event.foodTag(), event.dishType(),
                    event.quantity(), event.eventPit()
            ));
        }
    }
}
