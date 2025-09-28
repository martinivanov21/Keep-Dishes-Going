package be.kdg.keepdishesgoing.customerOrder.port.out;

import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrder;

public interface UpdateCustomerOrderPort {

    CustomerOrder update(CustomerOrder customerOrder);
}
