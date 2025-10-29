package be.kdg.keepdishesgoing.customerOrder.adapter.mapper;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.AddressProjectionDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.RestaurantBrowseDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.RestaurantDetailDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.ScheduleHourDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.MenuCustomerOrderJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.RestaurantCustomerOrderJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.ScheduleHourCustomerOrderJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.domain.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class RestaurantMapper {

    public Restaurant toDomain(RestaurantCustomerOrderJpaEntity entity) {
        return new Restaurant(
                new RestaurantId(entity.getRestaurantId()),
                entity.getRestaurantName(),
                entity.getCuisine(),
                entity.getPictureUrl(),
                entity.getWorkingHours() != null ?
                        entity.getWorkingHours().stream()
                                .map(this::scheduleHourToDomain)
                                .toList() : new ArrayList<>(),
                entity.getPriceRange() != null ? entity.getPriceRange() : PriceRange.REGULAR,
                entity.getAveragePrice() != null ? entity.getAveragePrice() : BigDecimal.ZERO,
                entity.getGuesstimatedDeliveryTimeMinutes(),
                entity.getMenu() != null ? MenuId.of(entity.getMenu().getMenuId().toString()) : null,
                entity.getOpeningStatus() != null ? entity.getOpeningStatus() : OpeningStatus.CLOSED,
                entity.getDeliveryStreet(),
                entity.getDeliveryNumber(),
                entity.getDeliveryCity()
        );
    }
    public RestaurantCustomerOrderJpaEntity toEntity(Restaurant restaurant) {
        RestaurantCustomerOrderJpaEntity entity = new RestaurantCustomerOrderJpaEntity();
        entity.setRestaurantId(restaurant.getRestaurantId().uuid());
        entity.setRestaurantName(restaurant.getRestaurantName());

        entity.setDeliveryStreet(restaurant.getDeliveryStreet());
        entity.setDeliveryNumber(restaurant.getDeliveryNumber());
        entity.setDeliveryCity(restaurant.getDeliveryCity());

        entity.setCuisine(restaurant.getCuisine());
        entity.setPictureUrl(restaurant.getPictureUrl());
        entity.setPriceRange(restaurant.getPriceRange());
        entity.setAveragePrice(restaurant.getAveragePrice());
        entity.setGuesstimatedDeliveryTimeMinutes(restaurant.getGuesstimatedDeliveryTimeMinutes());
        entity.setOpeningStatus(restaurant.getOpeningStatus());

        if (restaurant.getMenuId() != null) {
            MenuCustomerOrderJpaEntity menuEntity = new MenuCustomerOrderJpaEntity();
            menuEntity.setMenuId(UUID.fromString(restaurant.getMenuId().menuId().toString())); // Fixed: removed extra parenthesis
            entity.setMenu(menuEntity);
        }

        return entity;
    }

    public RestaurantDetailDto mapToRestaurantDetailDto(Restaurant restaurant) {
        return new RestaurantDetailDto(
                restaurant.getRestaurantId().uuid(),
                restaurant.getRestaurantName(),
                restaurant.getCuisine().name(),
                restaurant.getPictureUrl(),
                restaurant.getPriceRange().name(),
                restaurant.getAveragePrice().doubleValue(),
                restaurant.getGuesstimatedDeliveryTimeMinutes(),
                restaurant.getWorkingHours().stream()
                        .map(wh -> new ScheduleHourDto(
                                wh.getDayOfWeek().name(),
                                wh.getOpeningTime(),
                                wh.getClosingTime()
                        )).toList(),
                restaurant.getOpeningStatus().name(),
                new AddressProjectionDto(
                        restaurant.getDeliveryStreet(),
                        restaurant.getDeliveryNumber(),
                        restaurant.getDeliveryCity()
                )
        );
    }

    public RestaurantBrowseDto mapToRestaurantBrowseDto(Restaurant restaurant) {
        return new RestaurantBrowseDto(
                restaurant.getRestaurantId().uuid(),
                restaurant.getRestaurantName(),
                restaurant.getCuisine().name(),
                restaurant.getPictureUrl(),
                restaurant.getPriceRange().name(),
                restaurant.getAveragePrice().byteValueExact(),
                restaurant.getGuesstimatedDeliveryTimeMinutes(),
                restaurant.getWorkingHours().stream()
                        .map(wh -> new ScheduleHourDto(
                                wh.getDayOfWeek().name(),
                                wh.getOpeningTime(),
                                wh.getClosingTime()
                        )).toList()
        );
    }

    private ScheduleHour scheduleHourToDomain(ScheduleHourCustomerOrderJpaEntity entity) {
        return new ScheduleHour(
                ScheduleHourId.of(entity.getScheduleHourId().toString()),
                DayOfWeek.valueOf(entity.getDayOfWeek().name()),
                entity.getOpeningTime(),
                entity.getClosingTime()
        );
    }

}
