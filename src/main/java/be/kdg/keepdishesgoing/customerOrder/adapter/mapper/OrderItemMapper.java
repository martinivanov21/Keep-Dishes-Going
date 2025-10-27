package be.kdg.keepdishesgoing.customerOrder.adapter.mapper;

import be.kdg.keepdishesgoing.customerOrder.adapter.out.BasketJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.OrderItemJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.domain.DishId;
import be.kdg.keepdishesgoing.customerOrder.domain.OrderItem;
import be.kdg.keepdishesgoing.customerOrder.domain.OrderItemId;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemJpaEntity mapOrderItemToEntity(OrderItem orderItem, BasketJpaEntity basket) {
        OrderItemJpaEntity entity = new OrderItemJpaEntity();

        entity.setOrderItemId(orderItem.getOrderItemId().uuid());
        entity.setBasket(basket);
        entity.setDishId(orderItem.getDishId().uuid());
        entity.setDishName(orderItem.getDishName());
        entity.setUnitPrice(orderItem.getUnitPrice());
        entity.setQuantity(orderItem.getQuantity());
        entity.setPictureUrl(orderItem.getPictureUrl());

        return entity;
    }

    public OrderItem mapOrderItemToDomain(OrderItemJpaEntity entity) {
        return new OrderItem(
                OrderItemId.of(entity.getOrderItemId().toString()),
                DishId.of(entity.getDishId()),
                entity.getDishName(),
                entity.getUnitPrice(),
                entity.getQuantity(),
                entity.getPictureUrl()
        );
    }
}
