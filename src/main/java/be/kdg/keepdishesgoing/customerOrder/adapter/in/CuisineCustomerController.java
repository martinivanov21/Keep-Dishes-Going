package be.kdg.keepdishesgoing.customerOrder.adapter.in;

import be.kdg.keepdishesgoing.customerOrder.domain.Cuisine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants-customer/cuisines")
public class CuisineCustomerController {

    @GetMapping
    public Cuisine[] getAllCuisines() {
        return Cuisine.values();
    }
}
