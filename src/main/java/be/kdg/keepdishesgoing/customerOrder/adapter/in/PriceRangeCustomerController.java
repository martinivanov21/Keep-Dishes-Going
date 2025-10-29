package be.kdg.keepdishesgoing.customerOrder.adapter.in;

import be.kdg.keepdishesgoing.customerOrder.domain.DishType;
import be.kdg.keepdishesgoing.customerOrder.domain.PriceRange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants-customer/price-range")
public class PriceRangeCustomerController {

    @GetMapping
    public PriceRange[] getPriceRanges() {
        return PriceRange.values();
    }

}
