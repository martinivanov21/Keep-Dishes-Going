package be.kdg.keepdishesgoing.restaurant.adapter;

import be.kdg.keepdishesgoing.common.events.CustomerOrderAcceptedEvent;
import be.kdg.keepdishesgoing.common.events.CustomerOrderDeclinedEvent;
import be.kdg.keepdishesgoing.common.events.CustomerOrderItemQuantityChangeEvent;
import be.kdg.keepdishesgoing.common.events.CustomerOrderSubmittedEvent;
import be.kdg.keepdishesgoing.restaurant.adapter.mapper.RestaurantOrderMappter;
import be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant.RestaurantOrderItemJpaEntity;
import be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant.RestaurantOrderJpaEntity;
import be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant.RestaurantOrderRepository;
import be.kdg.keepdishesgoing.restaurant.domain.enums.OrderStatus;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.LoadRestaurantOrderPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantOrderPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerOrderProjectionListener {

    private static final Logger log = LoggerFactory.getLogger(CustomerOrderProjectionListener.class);
    private final RestaurantOrderRepository repo;
    private final SaveRestaurantOrderPort savePort;
    private final LoadRestaurantOrderPort loadPort;
    private final RestaurantOrderMappter mapper;


    public CustomerOrderProjectionListener(RestaurantOrderRepository repo, SaveRestaurantOrderPort savePort, LoadRestaurantOrderPort loadPort, RestaurantOrderMappter mapper) {
        this.repo = repo;
        this.savePort = savePort;
        this.loadPort = loadPort;
        this.mapper = mapper;
    }

    @EventListener
    @Transactional
    public void on(CustomerOrderSubmittedEvent e) {
        log.info("Projecting CustomerOrderSubmittedEvent {}", e.customerOrderId());

        var entity = new RestaurantOrderJpaEntity();
        entity.setCustomerOrderId(e.customerOrderId());
        entity.setRestaurantId(e.restaurantId());
        entity.setCustomerName(e.customerName());
        entity.setCustomerEmail(e.customerEmail());
        entity.setDeliveryStreet(e.deliveryStreet());
        entity.setDeliveryNumber(e.deliveryNumber());
        entity.setDeliveryCity(e.deliveryCity());
        entity.setTotalPrice(e.totalPrice());
        entity.setEstimatedTime(e.estimatedTime());
        entity.setSubmittedTime(e.submittedTime());
        entity.setOrderStatus(OrderStatus.PENDING);

        var items = e.items().stream().map(i -> {
            var it = new RestaurantOrderItemJpaEntity();
            it.setOrder(entity);
            it.setDishId(i.dishId());
            it.setDishName(i.dishName());
            it.setQuantity(i.quantity());
            it.setUnitPrice(i.unitPrice());
            it.setLineTotal(i.subtotal());
            return it;
        }).toList();

        entity.getItems().clear();
        entity.getItems().addAll(items);

        repo.save(entity);
    }

    @EventListener
    @Transactional
    public void on(CustomerOrderItemQuantityChangeEvent e) {
        var entity = repo.findById(e.customerOrderId())
                .orElseThrow(() -> new IllegalStateException("Projection missing for order " + e.customerOrderId()));
        var item = entity.getItems().stream()
                .filter(it -> it.getDishId().equals(e.dishId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Dish not found in projection"));
        item.setQuantity(e.newQuantity());
        item.setLineTotal(e.lineTotal());
        entity.setTotalPrice(e.orderTotal());
        repo.save(entity);
    }

    @EventListener
    @Transactional
    public void on(CustomerOrderAcceptedEvent e) {
        var entity = repo.findById(e.customerOrderId())
                .orElseThrow(() -> new IllegalStateException("Projection missing for order " + e.customerOrderId()));
        entity.setOrderStatus(OrderStatus.ACCEPTED);
        entity.setEstimatedTime(e.finalEstimatedTime());
        repo.save(entity);
    }

    @EventListener
    @Transactional
    public void on(CustomerOrderDeclinedEvent e) {
        var entity = repo.findById(e.customerOrderId())
                .orElseThrow(() -> new IllegalStateException("Projection missing for order " + e.customerOrderId()));
        entity.setOrderStatus(OrderStatus.REJECTED);
        savePort.save(mapper.toDomain(entity));
    }
}
