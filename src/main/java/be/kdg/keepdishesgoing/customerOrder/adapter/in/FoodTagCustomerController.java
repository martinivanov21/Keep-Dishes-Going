package be.kdg.keepdishesgoing.customerOrder.adapter.in;

import be.kdg.keepdishesgoing.customerOrder.domain.FoodTag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants-customer/foodtags")
public class FoodTagCustomerController {

    @GetMapping
    public FoodTag[] getFoodTags() {
        return FoodTag.values();
    }
}
