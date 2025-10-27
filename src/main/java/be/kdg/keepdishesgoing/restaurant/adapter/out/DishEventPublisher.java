package be.kdg.keepdishesgoing.restaurant.adapter.out;

import be.kdg.keepdishesgoing.common.events.DomainEvent;
import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.SaveDishPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DishEventPublisher implements SaveDishPort {

    private static final Logger logger = LoggerFactory.getLogger(DishEventPublisher.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public DishEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @Transactional
    public Dish saveDish(Dish dish) {
        List<DomainEvent> events = dish.getUncommittedEvents();

        for (DomainEvent event : events) {
            logger.info("Publishing event: {}", event.getClass().getSimpleName());
            applicationEventPublisher.publishEvent(event);
        }

        dish.clearEvents();
        return dish;
    }
}
