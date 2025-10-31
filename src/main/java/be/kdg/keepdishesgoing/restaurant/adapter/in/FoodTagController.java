package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.domain.enums.FoodTag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food-tags")
public class FoodTagController {

    @GetMapping
    public FoodTag[] getAllFoodTags() {
        return FoodTag.values();
    }
}
