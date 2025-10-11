package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateRestaurantRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.MenuDto;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.OwnerDto;
import be.kdg.keepdishesgoing.restaurant.domain.Menu;
import be.kdg.keepdishesgoing.restaurant.domain.MenuId;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateMenuCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateMenuUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final CreateMenuUseCase createMenuUseCase;

    public MenuController(CreateMenuUseCase createMenuUseCase) {
        this.createMenuUseCase = createMenuUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<MenuDto> createMenu(@RequestBody CreateRestaurantRequest request) {

        var menuId = MenuId.create();

        var command = new CreateMenuCommand(
                new Menu( menuId,
                        List.of())
        );

        Menu newMenu = createMenuUseCase.createMenu(command);

        return ResponseEntity.ok(new MenuDto(
                newMenu.getMenuId().uuid(),
                List.of()
        ));

    }
}
