package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrder;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveCustomerOrderPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomerOrderEventPublisher  {

    private static final Logger log = LoggerFactory.getLogger(CustomerOrderEventPublisher.class);
    private final ApplicationEventPublisher publisher;

    public CustomerOrderEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public CustomerOrder save(CustomerOrder order) {
        var events = order.getUncommittedEvents();
        log.info("Publishing {} events for order {}", events.size(), order.getCustomerOrderId());
        events.forEach(publisher::publishEvent);
        return order;
    }
}
