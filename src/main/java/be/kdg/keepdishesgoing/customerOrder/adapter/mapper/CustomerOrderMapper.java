package be.kdg.keepdishesgoing.customerOrder.adapter.mapper;

import be.kdg.keepdishesgoing.customerOrder.adapter.out.CustomerOrderJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.OrderItemJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.RestaurantCustomerOrderJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrder;
import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrderId;
import be.kdg.keepdishesgoing.customerOrder.domain.OrderItem;
import be.kdg.keepdishesgoing.customerOrder.domain.RestaurantId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerOrderMapper {

    private OrderItemMapper orderItemMapper;

    public CustomerOrder toDomain(CustomerOrderJpaEntity entity) {
        List<OrderItem> orderItems = entity.getOrderItems().stream()
                .map(orderItemMapper::orderItemToDomain)
                .toList();

        return new CustomerOrder(
                new CustomerOrderId(entity.getCustomerOrderId()),
                new RestaurantId(entity.getRestaurant().getRestaurantId()),
                orderItems,
                entity.getTotalPrice(),
                entity.getEstimateTime(),
                entity.getSubmittedTime(),
                entity.getOrderStatus(),
                entity.getDeliveryStreet(),
                entity.getDeliveryNumber(),
                entity.getDeliveryCity()
        );
    }

    public CustomerOrderJpaEntity toEntity(CustomerOrder order) {
        CustomerOrderJpaEntity entity = new CustomerOrderJpaEntity();
        entity.setCustomerOrderId(order.getCustomerOrderId().uuid());

        RestaurantCustomerOrderJpaEntity restaurantRef = new RestaurantCustomerOrderJpaEntity();
        restaurantRef.setRestaurantId(order.getRestaurantId().uuid());
        entity.setRestaurant(restaurantRef);

        entity.setDeliveryStreet(order.getDeliveryStreet());
        entity.setDeliveryNumber(order.getDeliveryStreetNumber());
        entity.setDeliveryCity(order.getDeliveryCity());
        entity.setEstimateTime(order.getEstimateTime());
        entity.setTotalPrice(order.getTotalPrice());
        entity.setSubmittedTime(order.getSubmittedTime());
        entity.setOrderStatus(order.getOrderStatus());

        List<OrderItemJpaEntity> itemEntities = order.getOrderItems().stream()
                .map(item -> orderItemMapper.orderItemToEntity(item, entity))
                .toList();
        entity.setOrderItems(itemEntities);

        return entity;
    }
}
