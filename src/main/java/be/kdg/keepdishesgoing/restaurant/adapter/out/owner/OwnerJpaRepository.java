package be.kdg.keepdishesgoing.restaurant.adapter.out.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OwnerJpaRepository  extends JpaRepository<OwnerJpaEntity, UUID> {
}
