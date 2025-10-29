package be.kdg.keepdishesgoing.customerOrder.adapter.in;

import be.kdg.keepdishesgoing.customerOrder.domain.DishType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants-customer/dish-types")
public class DishTypeCustomerController {

    @GetMapping
    public DishType[] getDishTypes() {
        return DishType.values();
    }
}
