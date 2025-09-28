package be.kdg.keepdishesgoing.customerOrder.adapter.in;

import be.kdg.keepdishesgoing.customerOrder.port.in.FindAllCustomerOrderPort;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerOrderController {

    private final FindAllCustomerOrderPort findAllCustomerOrderPort;

    public CustomerOrderController(FindAllCustomerOrderPort findAllCustomerOrderPort) {
        this.findAllCustomerOrderPort = findAllCustomerOrderPort;
    }
}
