package be.kdg.keepdishesgoing.customerOrder.adapter.mapper;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.BasketDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.BasketItemDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.BasketJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.BasketJpaRepository;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.OrderItemJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.domain.*;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadDishPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadRestaurantPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BasketMapper {

    private OrderItemMapper orderItemMapper;
    private final LoadRestaurantPort loadRestaurantPort;

    public BasketMapper(OrderItemMapper orderItemMapper, LoadRestaurantPort loadRestaurantPort) {
        this.orderItemMapper = orderItemMapper;

        this.loadRestaurantPort = loadRestaurantPort;
    }

    public Basket toDomain(BasketJpaEntity entity) {
        List<OrderItem> orderItems = entity.getItems() != null
                ? entity.getItems().stream()
                .filter(item -> item.getBasket() != null)
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

    public BasketDto toDto(Basket basket, LoadDishPort loadDishPort) {
        String restaurantName = null;
        if (basket.getRestaurantId() != null) {
            restaurantName = loadRestaurantPort.loadById(basket.getRestaurantId())
                    .map(Restaurant::getRestaurantName)
                    .orElse(null);
        }

        List<BasketItemDto> itemDtos = basket.getItems().stream()
                .map(item -> toItemDto(item, loadDishPort))
                .toList();

        return new BasketDto(
                basket.getBasketId().uuid(),
                basket.getRestaurantId() != null ? basket.getRestaurantId().uuid() : null,
                restaurantName,
                itemDtos,
                basket.getTotalPrice(),
                basket.getTotalItemCount(),
                basket.getStatus().name(),
                basket.getUpdatedAt()
        );
    }

    private BasketItemDto toItemDto(OrderItem item, LoadDishPort loadDishPort) {
        boolean available = loadDishPort.loadById(item.getDishId())
                .map(dish -> dish.getStatus() == DishStatus.PUBLISHED && dish.getQuantity() > 0)
                .orElse(false);

        return new BasketItemDto(
                item.getDishId().uuid(),
                item.getDishName(),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getSubtotal(),
                item.getPictureUrl(),
                available
        );
    }
}
