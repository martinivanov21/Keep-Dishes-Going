package be.kdg.keepdishesgoing.restaurant.adapter.out;

import be.kdg.keepdishesgoing.common.events.DomainEvent;
import be.kdg.keepdishesgoing.restaurant.domain.Menu;
import be.kdg.keepdishesgoing.restaurant.port.out.menu.SaveMenuPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuEventPublisher implements SaveMenuPort {

    private static final Logger logger =  LoggerFactory.getLogger(MenuEventPublisher.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public MenuEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }



    @Override
    @Transactional
    public Menu save(Menu menu) {
        List<DomainEvent> events = menu.getUncommittedEvents();

        events.forEach(applicationEventPublisher::publishEvent);
        menu.clearEvents();
        return menu;

    }
}
