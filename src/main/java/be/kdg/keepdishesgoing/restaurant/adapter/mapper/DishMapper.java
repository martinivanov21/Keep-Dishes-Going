package be.kdg.keepdishesgoing.restaurant.adapter.mapper;

import be.kdg.keepdishesgoing.restaurant.adapter.in.response.DishDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.DishVersionDto;
import be.kdg.keepdishesgoing.restaurant.domain.Dish;
import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {

    public DishDto mapToDishDto(Dish dish) {
        return new DishDto(
                dish.getDishId().uuid(),
                dish.getLiveVersion() != null ? mapToVersionDto(dish.getLiveVersion()) : null,
                dish.getDraftVersion() != null ? mapToVersionDto(dish.getDraftVersion()) : null,
                dish.getStatus(),
                dish.getQuantity(),
                dish.getMenu()
        );
    }

    public DishVersionDto mapToVersionDto(DishVersion version) {
        return new DishVersionDto(
                version.getDishVersionId().uuid(),
                version.getNameOfDish(),
                version.getDescription(),
                version.getPrice(),
                version.getPicture(),
                version.getPreparationTime(),
                version.getFoodTag(),
                version.getTypeOfDish()
        );
    }


}
