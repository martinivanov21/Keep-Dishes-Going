package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "schedule_hour_customer_order", schema = "kdg_customer_order")
public class ScheduleHourCustomerOrderJpaEntity {

    @Id
    @Column(name = "schedule_hour_id")
    private UUID scheduleHourId;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "opening_time", nullable = false)
    private LocalTime openingTime;
    @Column(name = "closing_time", nullable = false)
    private LocalTime closingTime;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantCustomerOrderJpaEntity restaurant;

    public ScheduleHourCustomerOrderJpaEntity() {
    }

    public UUID getScheduleHourId() {
        return scheduleHourId;
    }

    public void setScheduleHourId(UUID scheduleHourId) {
        this.scheduleHourId = scheduleHourId;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public RestaurantCustomerOrderJpaEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantCustomerOrderJpaEntity restaurant) {
        this.restaurant = restaurant;
    }
}
