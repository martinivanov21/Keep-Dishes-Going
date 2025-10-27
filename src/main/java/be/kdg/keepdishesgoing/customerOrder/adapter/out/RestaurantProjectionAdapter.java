package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.adapter.mapper.RestaurantMapper;
import be.kdg.keepdishesgoing.customerOrder.domain.Restaurant;
import be.kdg.keepdishesgoing.customerOrder.domain.RestaurantId;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadRestaurantPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveRestaurantPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RestaurantProjectionAdapter  implements SaveRestaurantPort, LoadRestaurantPort {
   private static final Logger logger = LoggerFactory.getLogger(RestaurantProjectionAdapter  .class);

   private final RestaurantProjectionJpaRepository restaurantJpaRepository;
   private final RestaurantMapper restaurantMapper;

    public RestaurantProjectionAdapter (RestaurantProjectionJpaRepository restaurantJpaRepository, RestaurantMapper restaurantMapper) {
        this.restaurantJpaRepository = restaurantJpaRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    @Transactional
    public Restaurant saveRestaurant(Restaurant restaurant) {
        logger.info("Saving restaurant projection: {}", restaurant.getRestaurantName());

        RestaurantCustomerOrderJpaEntity restaurantJpaEntity = restaurantMapper.toEntity(restaurant);
        RestaurantCustomerOrderJpaEntity saved = restaurantJpaRepository.save(restaurantJpaEntity);

        return restaurantMapper.toDomain(saved);
    }


    @Override
    public List<Restaurant> loadAll() {
        return restaurantJpaRepository.findAll().stream().map(restaurantMapper::toDomain).toList();
    }

    @Override
    public Optional<Restaurant> loadById(RestaurantId restaurantId) {
        return restaurantJpaRepository.findById(restaurantId.uuid()).map(restaurantMapper::toDomain);
    }
}
