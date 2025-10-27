package be.kdg.keepdishesgoing.customerOrder.adapter;

import be.kdg.keepdishesgoing.common.events.DishPublishedEvent;
import be.kdg.keepdishesgoing.common.events.DishUnpublishedEvent;
import be.kdg.keepdishesgoing.common.events.DishUpdatedEvent;
import be.kdg.keepdishesgoing.customerOrder.domain.*;
import be.kdg.keepdishesgoing.customerOrder.port.out.DeleteDishPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadDishPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadRestaurantPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveDishPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class DishProjectionEventListener {

    private static final Logger logger = LoggerFactory.getLogger(DishProjectionEventListener.class);

    private final SaveDishPort saveDishPort;
    private final DeleteDishPort deleteDishPort;
    private final LoadDishPort loadDishPort;
    private final LoadRestaurantPort loadRestaurantPort;

    public DishProjectionEventListener(SaveDishPort saveDishPort, DeleteDishPort deleteDishPort, LoadDishPort loadDishPort, LoadRestaurantPort loadRestaurantPort) {
        this.saveDishPort = saveDishPort;
        this.deleteDishPort = deleteDishPort;
        this.loadDishPort = loadDishPort;
        this.loadRestaurantPort = loadRestaurantPort;
    }

    @EventListener
    @Transactional
    public void handleDishPublished(DishPublishedEvent event) {
        logger.info("Received DishPublishedEvent for dish: {}", event.dishId());

        try {
            loadRestaurantPort.loadById(new RestaurantId(event.restaurantId()))
                    .orElseThrow(() -> new IllegalStateException(
                            "Restaurant not found in projection: " + event.restaurantId()));

            Dish dish = new Dish(
                    new DishId(event.dishId()),
                    event.nameOfDish(),
                    event.description(),
                    event.price(),
                    event.pictureUrl(),
                    event.preparationTime(),
                    FoodTag.valueOf(event.foodTag()),
                    DishType.valueOf(event.dishType()),
                    DishStatus.PUBLISHED,
                    event.quantity(),
                    new Menu(
                            new MenuId(UUID.randomUUID()),
                            new RestaurantId(event.restaurantId())
                    )
            );

            saveDishPort.saveDish(dish);

            logger.info("Dish projection created successfully for: {}", event.dishId());

        } catch (Exception e) {
            logger.error("Failed to project dish: {}", event.dishId(), e);
            throw e;
        }
    }
    @EventListener
    @Transactional
    public void handleDishUnpublished(DishUnpublishedEvent event) {
        logger.info("Received DishUnpublishedEvent for dish: {}", event.dishId());

        try {
            deleteDishPort.deleteDish(new DishId(event.dishId()));
            logger.info("Dish projection deleted successfully for: {}", event.dishId());
        } catch (Exception e) {
            logger.error("Failed to delete dish projection: {}", event.dishId(), e);
            throw e;
        }
    }



}