package be.kdg.keepdishesgoing.customerOrder.port.out;

import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrder;

public interface SaveCustomerOrderPort {
    CustomerOrder save(CustomerOrder customerOrder);
}
