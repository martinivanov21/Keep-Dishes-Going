package be.kdg.keepdishesgoing.restaurant.adapter.mapper;

import be.kdg.keepdishesgoing.restaurant.adapter.in.response.MenuDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.RestaurantDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.WorkingHourDto;
import be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant.RestaurantJpaEntity;
import be.kdg.keepdishesgoing.restaurant.domain.*;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class RestaurantOwnerMapper {

    public Restaurant toDomain(RestaurantJpaEntity e) {
        return new Restaurant(
                new RestaurantId(e.getRestaurantId()),
                e.getNameOfRestaurant(),
                e.getCuisine(),
                e.getOpeningStatus(),
                e.getDefaultPreparationTime(),
                e.getContactEmail(),
                e.getPicture(),
                e.getAddress() != null ? new AddressId(e.getAddress().getAddressId()) : null,
                e.getOwner()   != null ? new OwnerId(e.getOwner().getOwnerId())       : null,
                e.getMenu()    != null ? new MenuId(e.getMenu().getMenuId())          : null,
                e.getWorkingHours() == null ? List.of() :
                        e.getWorkingHours().stream()
                                .map(wh -> new WorkingHour(
                                        wh.getDayOfWeek(),
                                        wh.getOpeningTime(),
                                        wh.getClosingTime()))
                                .sorted(Comparator.comparing(WorkingHour::getDayOfWeek))
                                .toList()
        );
    }

    public RestaurantDto toDto(Restaurant r) {
        return new RestaurantDto(
                r.getRestaurantId().uuid(),
                r.getNameOfRestaurant(),
                r.getCuisine().name(),
                r.getDefaultPreparationTime(),
                r.getContactEmail(),
                r.getPicture(),
                r.getOpeningStatus().name(),
                r.getAddressId() != null ? r.getAddressId().uuid() : null,
                r.getWorkingHours() == null ? List.of() :
                        r.getWorkingHours().stream()
                                .map(wh -> new WorkingHourDto(
                                        wh.getDayOfWeek().name(),
                                        wh.getOpeningTime().toString(),
                                        wh.getClosingTime().toString()
                                )).toList(),
                r.getOwnerId() != null ? r.getOwnerId().uuid() : null,
                r.getMenuId() != null ? new MenuDto(r.getMenuId().uuid(), r.getRestaurantId().uuid()) : null
        );
    }





}
