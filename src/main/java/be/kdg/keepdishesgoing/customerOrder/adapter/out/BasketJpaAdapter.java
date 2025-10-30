package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.adapter.mapper.BasketMapper;
import be.kdg.keepdishesgoing.customerOrder.domain.Basket;
import be.kdg.keepdishesgoing.customerOrder.domain.BasketId;
import be.kdg.keepdishesgoing.customerOrder.domain.OrderItem;
import be.kdg.keepdishesgoing.customerOrder.port.out.DeleteBasketPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadBasketPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveBasketPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BasketJpaAdapter implements LoadBasketPort, SaveBasketPort, DeleteBasketPort {

    private final BasketJpaRepository basketRepository;
    private final BasketMapper basketMapper;

    public BasketJpaAdapter(BasketJpaRepository basketRepository, BasketMapper basketMapper) {
        this.basketRepository = basketRepository;
        this.basketMapper = basketMapper;
    }

    @Override
    @Transactional
    public void delete(BasketId basketId) {
        basketRepository.deleteById(basketId.uuid());
    }

    @Override
    public Optional<Basket> loadById(BasketId basketId) {
        return basketRepository.findById(basketId.uuid())
                .map(basketMapper::toDomain);
    }

    @Override
    @Transactional
    public Basket save(Basket basket) {
        Optional<BasketJpaEntity> existingEntity = basketRepository.findById(basket.getBasketId().uuid());

        BasketJpaEntity entity;
        if (existingEntity.isPresent()) {
            entity = existingEntity.get();
            updateBasketEntity(entity, basket);
        } else {
            entity = basketMapper.toEntity(basket);
        }

        BasketJpaEntity saved = basketRepository.save(entity);
        return basketMapper.toDomain(saved);
    }

    private void updateBasketEntity(BasketJpaEntity entity, Basket basket) {
        entity.setRestaurantId(basket.getRestaurantId() != null ? basket.getRestaurantId().uuid() : null);
        entity.setOrderStatus(basket.getStatus());
        entity.setUpdatedAt(basket.getUpdatedAt());

        entity.getItems().clear();

        if (basket.getItems() != null && !basket.getItems().isEmpty()) {
            for (OrderItem item : basket.getItems()) {
                OrderItemJpaEntity itemEntity = new OrderItemJpaEntity();
                itemEntity.setOrderItemId(item.getOrderItemId().uuid());
                itemEntity.setBasket(entity);
                itemEntity.setDishId(item.getDishId().uuid());
                itemEntity.setDishName(item.getDishName());
                itemEntity.setUnitPrice(item.getUnitPrice());
                itemEntity.setQuantity(item.getQuantity());
                itemEntity.setPictureUrl(item.getPictureUrl());
                entity.getItems().add(itemEntity);
            }
        }
    }
}
