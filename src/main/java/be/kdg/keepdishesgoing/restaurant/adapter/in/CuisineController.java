package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.domain.enums.Cuisine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cuisines")
public class CuisineController {

    @GetMapping
    public Cuisine[] getAllCuisines() {
        return Cuisine.values();
    }
}
