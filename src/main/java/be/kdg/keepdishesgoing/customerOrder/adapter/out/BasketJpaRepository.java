package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BasketJpaRepository extends JpaRepository<BasketJpaEntity, UUID> {
}
