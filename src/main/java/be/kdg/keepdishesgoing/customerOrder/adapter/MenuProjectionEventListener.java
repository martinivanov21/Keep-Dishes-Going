package be.kdg.keepdishesgoing.customerOrder.adapter;

import be.kdg.keepdishesgoing.common.events.MenuCreatedEvent;
import be.kdg.keepdishesgoing.common.events.MenuUpdatedEvent;
import be.kdg.keepdishesgoing.customerOrder.domain.Menu;
import be.kdg.keepdishesgoing.customerOrder.domain.MenuId;
import be.kdg.keepdishesgoing.customerOrder.domain.RestaurantId;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadMenuPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveMenuPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuProjectionEventListener {

    private static final Logger logger = LoggerFactory.getLogger(MenuProjectionEventListener.class);

    private final SaveMenuPort saveMenuPort;
    private final LoadMenuPort loadMenuPort;

    public MenuProjectionEventListener(SaveMenuPort saveMenuPort, LoadMenuPort loadMenuPort) {
        this.saveMenuPort = saveMenuPort;
        this.loadMenuPort = loadMenuPort;
    }

    @EventListener
    @Transactional
    public void handleMenuCreated(MenuCreatedEvent event) {
        logger.info("Received MenuCreatedEvent for menu: {} and restaurant: {}",
                event.menuId(), event.restaurantId());

        Menu menu = new Menu(
                new MenuId(event.menuId()),
                new RestaurantId(event.restaurantId())
        );

        saveMenuPort.save(menu);
        logger.info("Menu projection saved successfully at {}", event.eventPit());
    }

    @EventListener
    @Transactional
    public void handleMenuUpdated(MenuUpdatedEvent event) {
        logger.info("Received MenuUpdatedEvent for menu: {}", event.menuId());

        Menu existingMenu = loadMenuPort.loadById(new MenuId(event.menuId()));

        if (existingMenu != null) {
            saveMenuPort.save(existingMenu);
            logger.info("Menu projection updated successfully at {}", event.eventPit());
        } else {
            logger.warn("Menu not found in projection: {}", event.menuId());
        }
    }
}
