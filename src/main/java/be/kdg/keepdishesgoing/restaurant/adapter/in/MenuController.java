package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateMenuRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateRestaurantRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.MenuDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.OwnerDto;
import be.kdg.keepdishesgoing.restaurant.domain.Menu;
import be.kdg.keepdishesgoing.restaurant.domain.MenuId;
import be.kdg.keepdishesgoing.restaurant.domain.RestaurantId;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateMenuCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateMenuUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/menus")
public class MenuController {

    private final CreateMenuUseCase createMenuUseCase;

    public MenuController(CreateMenuUseCase createMenuUseCase) {
        this.createMenuUseCase = createMenuUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<MenuDto> createMenu(@PathVariable UUID restaurantId) {

        var menuId = MenuId.create();

        var command = new CreateMenuCommand(
                new Menu( menuId,
                        RestaurantId.of(restaurantId),
                        List.of()
        ));

        Menu newMenu = createMenuUseCase.createMenu(command);

        return ResponseEntity.ok(new MenuDto(
                newMenu.getMenuId().uuid(),
                newMenu.getRestaurantId().uuid()
        ));

    }
}
