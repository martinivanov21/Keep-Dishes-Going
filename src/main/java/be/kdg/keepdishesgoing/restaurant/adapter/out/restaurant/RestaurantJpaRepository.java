package be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant;

import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantJpaEntity, UUID> {

    Optional<RestaurantJpaEntity> findByOwner_OwnerId(UUID ownerOwnerId);

//    Optional<RestaurantJpaEntity> findByOwner(UUID ownerId);
}
