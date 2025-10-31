package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.domain.enums.DishType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dish-types")
public class DishTypeController {

    @GetMapping
    public DishType[] getAllDishTypes() {
        return DishType.values();
    }
}
