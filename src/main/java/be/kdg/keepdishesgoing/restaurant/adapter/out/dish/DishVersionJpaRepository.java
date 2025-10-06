package be.kdg.keepdishesgoing.restaurant.adapter.out.dish;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DishVersionJpaRepository extends JpaRepository<DishVersionJpaEntity, UUID> {
}
