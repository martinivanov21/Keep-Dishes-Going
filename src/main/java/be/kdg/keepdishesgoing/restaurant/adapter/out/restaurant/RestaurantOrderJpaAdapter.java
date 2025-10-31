package be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant;

import be.kdg.keepdishesgoing.restaurant.adapter.mapper.RestaurantOrderMappter;
import be.kdg.keepdishesgoing.restaurant.adapter.mapper.RestaurantOwnerMapper;
import be.kdg.keepdishesgoing.restaurant.domain.RestaurantOrder;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.LoadRestaurantOrderPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantOrderPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RestaurantOrderJpaAdapter implements SaveRestaurantOrderPort, LoadRestaurantOrderPort {

    private static final Logger log = LoggerFactory.getLogger(RestaurantOrderJpaAdapter.class);
    private final RestaurantOrderRepository repo;
    private final RestaurantOrderMappter mapper;

    public RestaurantOrderJpaAdapter(RestaurantOrderRepository repo, RestaurantOrderMappter mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    @Override
    @Transactional
    public RestaurantOrder save(RestaurantOrder order) {
        var existing = repo.findById(order.getCustomerOrderId());
        RestaurantOrderJpaEntity entity;
        if (existing.isPresent()) {
            entity = existing.get();
            mapper.merge(order, entity);
        } else {
            entity = mapper.toJpa(order);
        }
        var saved = repo.save(entity);
        log.debug("Saved projection for order {}", saved.getCustomerOrderId());
        return mapper.toDomain(saved);
    }

    @Override
    @Transactional
    public Optional<RestaurantOrder> load(UUID customerOrderId) {
        return repo.findById(customerOrderId).map(mapper::toDomain);
    }
}
