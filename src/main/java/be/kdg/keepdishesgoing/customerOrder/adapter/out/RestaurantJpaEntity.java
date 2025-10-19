package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.domain.*;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.ScheduleHourJpaEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurant_order", schema = "kdg_customerOrder")
public class RestaurantJpaEntity {

    @Id
    @Column(name = "restaurant_id",nullable = false)
    private UUID restaurantId;

    private AddressJpaEntity address;
    private String restaurantName;
    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;
    private String pictureUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    private MenuJpaEntity menu;
    @OneToMany(mappedBy = "restaurant_order")
    private List<ScheduleHourJpaEntity> scheduleHours;
    private BigDecimal averagePrice;
    private PriceRange priceRange;
    private int guesstimatedDeliveryTimeMinutes;
    private List<Dish> publishedDishes;
}
