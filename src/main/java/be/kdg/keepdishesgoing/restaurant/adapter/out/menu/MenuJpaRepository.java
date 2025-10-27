package be.kdg.keepdishesgoing.restaurant.adapter.out.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface MenuJpaRepository extends JpaRepository<MenuJpaEntity, UUID> {

    @Query("SELECT m FROM MenuJpaEntity m LEFT JOIN FETCH m.restaurant WHERE m.menuId = :menuId")
    Optional<MenuJpaEntity> findByIdWithRestaurant(@Param("menuId") UUID menuId);
}
