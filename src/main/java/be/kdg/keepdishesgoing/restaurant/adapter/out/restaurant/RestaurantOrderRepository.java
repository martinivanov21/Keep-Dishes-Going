package be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantOrderRepository extends JpaRepository<RestaurantOrderJpaEntity, UUID> {

    Optional<RestaurantOrderJpaEntity> findByCustomerOrderIdAndRestaurantId(UUID customerOrderId, UUID restaurantId);


}
