package be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantJpaEntity, UUID> {
}
