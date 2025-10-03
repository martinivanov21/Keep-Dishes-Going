package be.kdg.keepdishesgoing.restaurant.adapter.out.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressJpaRepository extends JpaRepository<AddressJpaRepository, UUID> {
}
