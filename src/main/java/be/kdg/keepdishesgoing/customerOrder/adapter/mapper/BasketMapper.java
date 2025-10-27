package be.kdg.keepdishesgoing.customerOrder.adapter.mapper;

import be.kdg.keepdishesgoing.customerOrder.adapter.out.BasketJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.BasketJpaRepository;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.OrderItemJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.domain.Basket;
import be.kdg.keepdishesgoing.customerOrder.domain.BasketId;
import be.kdg.keepdishesgoing.customerOrder.domain.OrderItem;
import be.kdg.keepdishesgoing.customerOrder.domain.RestaurantId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BasketMapper {

    private OrderItemMapper orderItemMapper;

    public Basket toDomain(BasketJpaEntity entity) {
        List<OrderItem> items = entity.getItems() != null
                ? entity.getItems().stream()
                .map(orderItemMapper::mapOrderItemToDomain)
                .collect(Collectors.toList())
                : new ArrayList<>();

        RestaurantId restaurantId = entity.getRestaurantId() != null
                ? RestaurantId.of(entity.getRestaurantId())
                : null;

        return new Basket(
                BasketId.of(entity.getBasketId().toString()),
                restaurantId,
                items,
                entity.getOrderStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public BasketJpaEntity toEntity(Basket basket) {
        BasketJpaEntity entity = new BasketJpaEntity();

        entity.setBasketId(basket.getBasketId().uuid());
        entity.setRestaurantId(basket.getRestaurantId() != null ? basket.getRestaurantId().uuid() : null);
        entity.setOrderStatus(basket.getStatus());
        entity.setCreatedAt(basket.getCreatedAt());
        entity.setUpdatedAt(basket.getUpdatedAt());

        if (basket.getItems() != null && !basket.getItems().isEmpty()) {
            List<OrderItemJpaEntity> itemEntities = basket.getItems().stream()
                    .map(item -> orderItemMapper.mapOrderItemToEntity(item, entity))
                    .collect(Collectors.toList());
            entity.setItems(itemEntities);
        }

        return entity;
    }
}
