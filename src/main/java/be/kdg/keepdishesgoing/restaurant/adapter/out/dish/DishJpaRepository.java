package be.kdg.keepdishesgoing.restaurant.adapter.out.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DishJpaRepository extends JpaRepository<DishJpaEntity, UUID> {

    @Query("SELECT d FROM DishJpaEntity d WHERE d.menu.restaurant.restaurantId = :restaurantId")
    List<DishJpaEntity> findDishesByRestaurantId(@Param("restaurantId") UUID restaurantId);
}
