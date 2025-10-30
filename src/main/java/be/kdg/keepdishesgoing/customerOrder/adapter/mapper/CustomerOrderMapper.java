package be.kdg.keepdishesgoing.customerOrder.adapter.mapper;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.CustomerOrderDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.OrderItemDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.CustomerOrderJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.OrderItemJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.RestaurantCustomerOrderJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.domain.*;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadRestaurantPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerOrderMapper {

    private final OrderItemMapper orderItemMapper;
    private final LoadRestaurantPort loadRestaurantPort;

    public CustomerOrderMapper(OrderItemMapper orderItemMapper, LoadRestaurantPort loadRestaurantPort) {
        this.orderItemMapper = orderItemMapper;
        this.loadRestaurantPort = loadRestaurantPort;
    }

    public CustomerOrder toDomain(CustomerOrderJpaEntity entity) {
        List<OrderItem> orderItems = entity.getOrderItems().stream()
                .filter(item -> item.getCustomerOrder() != null)
                .map(orderItemMapper::orderItemToDomain)
                .toList();

        return new CustomerOrder(
                new CustomerOrderId(entity.getCustomerOrderId()),
                new RestaurantId(entity.getRestaurant().getRestaurantId()),
                orderItems,
                entity.getCustomerName(),
                entity.getCustomerEmail(),
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

        entity.setCustomerName(order.getCustomerName());
        entity.setCustomerEmail(order.getCustomerEmail());
        entity.setDeliveryStreet(order.getDeliveryStreet());
        entity.setDeliveryNumber(order.getDeliveryStreetNumber());
        entity.setDeliveryCity(order.getDeliveryCity());
        entity.setEstimateTime(order.getEstimateTime());
        entity.setTotalPrice(order.getTotalPrice());
        entity.setSubmittedTime(order.getSubmittedTime());
        entity.setOrderStatus(order.getOrderStatus());

        List<OrderItemJpaEntity> itemEntities = new ArrayList<>();
        for (OrderItem item : order.getOrderItems()) {
            OrderItemJpaEntity itemEntity = orderItemMapper.orderItemToEntity(item, entity);
            itemEntities.add(itemEntity);
        }
        entity.setOrderItems(itemEntities);

        return entity;
    }

    public CustomerOrderDto toDto(CustomerOrder order) {
        String restaurantName = loadRestaurantPort.loadById(order.getRestaurantId())
                .map(Restaurant::getRestaurantName)
                .orElse("Unknown Restaurant");

        List<OrderItemDto> itemDtos = order.getOrderItems().stream()
                .map(this::toItemDto)
                .toList();

        return new CustomerOrderDto(
                order.getCustomerOrderId().uuid(),
                order.getRestaurantId().uuid(),
                restaurantName,
                order.getCustomerName(),
                order.getCustomerEmail(),
                order.getFullDeliveryAddress(),
                itemDtos,
                order.getTotalPrice(),
                order.getEstimateTime(),
                order.getOrderStatus().name(),
                order.getSubmittedTime()
        );
    }

    private OrderItemDto toItemDto(OrderItem item) {
        return new OrderItemDto(
                item.getDishId().uuid(),
                item.getDishName(),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getSubtotal(),
                item.getPictureUrl()
        );
    }


}
