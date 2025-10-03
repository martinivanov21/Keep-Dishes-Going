package be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant;

import be.kdg.keepdishesgoing.restaurant.domain.ScheduleHour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleHourJpaRepository extends JpaRepository<ScheduleHourJpaEntity, UUID> {
}
