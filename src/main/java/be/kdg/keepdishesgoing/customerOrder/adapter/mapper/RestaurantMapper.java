package be.kdg.keepdishesgoing.customerOrder.adapter.mapper;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.RestaurantBrowseDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.RestaurantDetailDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.ScheduleHourDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.RestaurantCustomerOrderJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.domain.Restaurant;
import be.kdg.keepdishesgoing.customerOrder.domain.RestaurantId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class RestaurantMapper {

    public Restaurant toDomain(RestaurantCustomerOrderJpaEntity entity) {
        return new Restaurant(
                new RestaurantId(UUID.randomUUID()),
                entity.getRestaurantName(),
                null,
                entity.getCuisine(),
                entity.getPictureUrl(),
                new ArrayList<>(),
                entity.getPriceRange(),
                entity.getAveragePrice(),
                entity.getGuesstimatedDeliveryTimeMinutes(),
                new ArrayList<>()
        );
    }

    public RestaurantCustomerOrderJpaEntity toEntity(Restaurant restaurant) {
        RestaurantCustomerOrderJpaEntity entity = new RestaurantCustomerOrderJpaEntity();
        entity.setRestaurantId(RestaurantId.of(restaurant.getRestaurantId().uuid()).uuid());
        entity.setRestaurantName(restaurant.getRestaurantName());
        entity.setCuisine(restaurant.getCuisine());
        entity.setPictureUrl(restaurant.getPictureUrl());
        entity.setPriceRange(restaurant.getPriceRange());
        entity.setAveragePrice(restaurant.getAveragePrice());
        entity.setGuesstimatedDeliveryTimeMinutes(restaurant.getGuesstimatedDeliveryTimeMinutes());
        return entity;
    }

    public RestaurantDetailDto mapToRestaurantDetailDto(Restaurant restaurant) {
        return new RestaurantDetailDto(
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
                        )).toList(),
                null // Address
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

}
