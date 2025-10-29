package be.kdg.keepdishesgoing.customerOrder.adapter.mapper;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.DishBrowseDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.DishDetailDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.DishCustomerOrderJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.adapter.out.MenuCustomerOrderJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.domain.*;
import org.springframework.stereotype.Component;

@Component
public class DishMapperProjection {

    public Dish toDomain(DishCustomerOrderJpaEntity entity) {
        Menu menu = null;
        if (entity.getMenu() != null) {
            menu = new Menu(
                    MenuId.of(entity.getMenu().getMenuId().toString()),
                    RestaurantId.of(entity.getMenu().getRestaurantId())
            );
        }
        return new Dish(
                new DishId(entity.getDishId()),
                new MenuId(entity.getMenu().getMenuId()),
                entity.getNameOfDish(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getPictureUrl(),
                entity.getPreparationTime(),
                entity.getFoodTag(),
                entity.getDishType(),
                entity.getDishStatus(),
                entity.getQuantity()
        );
    }

    public DishCustomerOrderJpaEntity toEntity(Dish dish) {

        DishCustomerOrderJpaEntity entity = new DishCustomerOrderJpaEntity();

        entity.setDishId(dish.getDishId().uuid());
        entity.setNameOfDish(dish.getNameOfDish());
        entity.setDescription(dish.getDescription());
        entity.setPrice(dish.getPrice());
        entity.setPictureUrl(dish.getPictureUrl());
        entity.setPreparationTime(dish.getPreparationTime());
        entity.setFoodTag(dish.getFoodTag());
        entity.setDishType(dish.getDishType());
        entity.setDishStatus(dish.getStatus());
        entity.setQuantity(dish.getQuantity());

        entity.setMenu(null);
//        if (dish.getMenu() != null) {
//            MenuCustomerOrderJpaEntity menuEntity = new MenuCustomerOrderJpaEntity();
//            menuEntity.setMenuId(dish.getMenu().getMenuId().menuId());
//            menuEntity.setRestaurantId(dish.getMenu().getRestaurantId().uuid());
//            entity.setMenu(menuEntity);
//        }

        return entity;
    }

    public DishDetailDto mapToDishDetailDto(Dish dish) {
        return new DishDetailDto(
                dish.getDishId().uuid(),
                dish.getNameOfDish(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getPictureUrl(),
                dish.getPreparationTime(),
                dish.getFoodTag().name(),
                dish.getDishType().name(),
                dish.getQuantity(),
                dish.getQuantity() > 0
        );
    }
    public DishBrowseDto mapToDishBrowseDto(Dish dish) {
        return new DishBrowseDto(
                dish.getDishId().uuid(),
                dish.getNameOfDish(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getPictureUrl(),
                dish.getPreparationTime(),
                dish.getFoodTag().name(),
                dish.getDishType().name(),
                dish.getQuantity() > 0
        );
    }

}
