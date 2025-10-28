package be.kdg.keepdishesgoing.customerOrder.adapter.mapper;

import be.kdg.keepdishesgoing.customerOrder.adapter.out.MenuCustomerOrderJpaEntity;
import be.kdg.keepdishesgoing.customerOrder.domain.Menu;
import be.kdg.keepdishesgoing.customerOrder.domain.MenuId;
import be.kdg.keepdishesgoing.customerOrder.domain.RestaurantId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuProjectionMapper {

    public MenuCustomerOrderJpaEntity toEntity(Menu menu) {
        MenuCustomerOrderJpaEntity entity = new MenuCustomerOrderJpaEntity();
        entity.setMenuId(menu.getMenuId().menuId());
        entity.setRestaurantId(menu.getRestaurantId().uuid());
        return entity;
    }

    public Menu toDomain(MenuCustomerOrderJpaEntity entity) {
        return new Menu(
                new MenuId(entity.getMenuId()),
                new RestaurantId(entity.getRestaurantId())
        );
    }
}
