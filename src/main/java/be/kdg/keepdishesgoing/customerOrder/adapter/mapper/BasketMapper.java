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

    public BasketMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;

    }

    public Basket toDomain(BasketJpaEntity entity) {
        List<OrderItem> orderItems = entity.getItems() != null
                ? entity.getItems().stream()
                .map(orderItemMapper::orderItemToDomain)
                .toList()
                : new ArrayList<>();

        return new Basket(
                new BasketId(entity.getBasketId()),
                entity.getRestaurantId() != null ? new RestaurantId(entity.getRestaurantId()) : null,
                orderItems,
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
                    .map(item -> orderItemMapper.toEntity(item, entity))
                    .toList();
            entity.setItems(itemEntities);
        }

        return entity;
    }
}
