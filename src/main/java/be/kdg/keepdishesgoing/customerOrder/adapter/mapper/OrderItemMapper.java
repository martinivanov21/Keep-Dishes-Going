package be.kdg.keepdishesgoing.customerOrder.adapter.mapper;

import be.kdg.keepdishesgoing.customerOrder.adapter.out.BasketJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.CustomerOrderJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.OrderItemJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.domain.DishId;
import be.kdg.keepdishesgoing.customerOrder.domain.OrderItem;
import be.kdg.keepdishesgoing.customerOrder.domain.OrderItemId;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemJpaEntity orderItemToEntity(OrderItem item, CustomerOrderJpaEntity orderEntity) {
        OrderItemJpaEntity entity = new OrderItemJpaEntity();
        entity.setOrderItemId(item.getOrderItemId().uuid());
        entity.setCustomerOrder(orderEntity);
        entity.setDishId(item.getDishId().uuid());
        entity.setDishName(item.getDishName());
        entity.setUnitPrice(item.getUnitPrice());
        entity.setQuantity(item.getQuantity());
        entity.setPictureUrl(item.getPictureUrl());
        return entity;
    }

    public OrderItem orderItemToDomain(OrderItemJpaEntity entity) {
        return new OrderItem(
                new OrderItemId(entity.getOrderItemId()),
                new DishId(entity.getDishId()),
                entity.getDishName(),
                entity.getUnitPrice(),
                entity.getQuantity(),
                entity.getPictureUrl()
        );
    }

    public OrderItemJpaEntity toEntity(OrderItem item, BasketJpaEntity basketEntity) {
        OrderItemJpaEntity entity = new OrderItemJpaEntity();
        entity.setOrderItemId(item.getOrderItemId().uuid());
        entity.setBasket(basketEntity);
        entity.setDishId(item.getDishId().uuid());
        entity.setDishName(item.getDishName());
        entity.setUnitPrice(item.getUnitPrice());
        entity.setQuantity(item.getQuantity());
        entity.setPictureUrl(item.getPictureUrl());
        return entity;
    }
}
