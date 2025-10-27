package be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant;

import be.kdg.keepdishesgoing.restaurant.adapter.out.address.AddressJpaEntity;
import be.kdg.keepdishesgoing.restaurant.adapter.out.menu.MenuJpaEntity;
import be.kdg.keepdishesgoing.restaurant.adapter.out.owner.OwnerJpaEntity;
import be.kdg.keepdishesgoing.restaurant.domain.*;

import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.DeleteRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.LoadRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.SaveRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.UpdateRestaurantPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class RestaurantJpaAdapter implements LoadRestaurantPort, UpdateRestaurantPort, SaveRestaurantPort,
        DeleteRestaurantPort {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantJpaAdapter.class);
    private final RestaurantJpaRepository restaurants;

    public RestaurantJpaAdapter(RestaurantJpaRepository restaurants) {
        this.restaurants = restaurants;
    }




    @Override
    @Transactional
    public Restaurant saveRestaurant(Restaurant restaurant) {
        logger.info("Saving restaurant {}", restaurant);

        RestaurantJpaEntity restaurantJpaEntity = mapToEntity(restaurant);
        RestaurantJpaEntity result = restaurants.save(restaurantJpaEntity);

        return mapToDomain(result);
    }


    @Override
    public Optional<Restaurant> loadBy(OwnerId ownerId) {
        logger.info("Loading restaurant by {}", ownerId);
        return restaurants.findByOwner_OwnerId(ownerId.uuid())
                .map(this::mapToDomain);
    }



    @Override
    public List<Restaurant> loadAll() {
        return restaurants.findAll().stream().map(this::mapToDomain).toList();
    }

    @Override
    @Transactional
    public Restaurant update(Restaurant restaurant) {
        logger.info("Updating restaurant {}", restaurant);
        RestaurantJpaEntity entity = mapToEntity(restaurant);
        RestaurantJpaEntity update = restaurants.save(entity);

        return mapToDomain(update);
    }

    @Override
    @Transactional
    public void delete(RestaurantId restaurantId) {
        restaurants.deleteById(restaurantId.uuid());
    }


    private RestaurantJpaEntity mapToEntity(Restaurant restaurant) {
        RestaurantJpaEntity entity = new RestaurantJpaEntity();

        entity.setRestaurantId(restaurant.getRestaurantId().uuid());
        entity.setNameOfRestaurant(restaurant.getNameOfRestaurant());
        entity.setCuisine(restaurant.getCuisine());
        entity.setOpeningStatus(restaurant.getOpeningStatus());
        entity.setDefaultPreparationTime(restaurant.getDefaultPreparationTime());
        entity.setContactEmail(restaurant.getContactEmail());
        entity.setPicture(restaurant.getPicture());

        if (restaurant.getAddressId() != null) {
            AddressJpaEntity address = new AddressJpaEntity();
            address.setAddressId(restaurant.getAddressId().uuid());
            entity.setAddress(address);
        }

        if (restaurant.getOwnerId() != null) {
            OwnerJpaEntity owner = new OwnerJpaEntity();
            owner.setOwnerId(restaurant.getOwnerId().uuid());
            entity.setOwner(owner);
        }

        if (restaurant.getMenu() != null && restaurant.getMenu().getMenuId() != null) {
            MenuJpaEntity menu = new MenuJpaEntity();
            menu.setMenuId(restaurant.getMenu().getMenuId().uuid());
            entity.setMenu(menu);
        }

        return entity;
    }

    private Restaurant mapToDomain(RestaurantJpaEntity entity) {
        return new Restaurant(
                new RestaurantId(entity.getRestaurantId()),
                entity.getNameOfRestaurant(),
                entity.getCuisine(),
                entity.getOpeningStatus(),
                entity.getDefaultPreparationTime(),
                entity.getContactEmail(),
                entity.getPicture(),
                entity.getAddress() != null ? new AddressId(entity.getAddress().getAddressId()) : null,
                entity.getOwner() != null ? new OwnerId(entity.getOwner().getOwnerId()) : null,
                entity.getMenu() != null ? mapMenu(entity.getMenu()) : null,
                entity.getWorkingHours().stream()
                        .map(this::mapScheduleHour)
                        .filter(Objects::nonNull)
                        .toList()
        );
    }

    private Menu mapMenu(MenuJpaEntity entity) {
        if (entity == null) return null;

        List<DishId> dishIds = entity.getDishes().stream()
                .map(dish -> new DishId(dish.getDishId()))
                .toList();

        return new Menu(new MenuId(entity.getMenuId()), dishIds);
    }

    private ScheduleHour mapScheduleHour(ScheduleHourJpaEntity entity) {
        if (entity == null) return null;

        return new ScheduleHour(
                new ScheduleHourId(entity.getScheduleHourId()),
                entity.getDayOfWeek(),
                entity.getOpeningTime(),
                entity.getClosingTime()
        );
    }

}
