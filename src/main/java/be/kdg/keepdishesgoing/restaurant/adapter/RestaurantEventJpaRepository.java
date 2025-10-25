package be.kdg.keepdishesgoing.restaurant.adapter;

import be.kdg.keepdishesgoing.restaurant.adapter.out.RestaurantEventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RestaurantEventJpaRepository extends JpaRepository<RestaurantEventJpaEntity, UUID> {

    List<RestaurantEventJpaEntity> findByRestaurantUuidOrderByEventPitAsc(UUID restaurantUuid);
}
