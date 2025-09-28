package be.kdg.keepdishesgoing.customerOrder.port.in;

import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrder;

import java.util.List;

public interface FindAllCustomerOrderPort {

    List<CustomerOrder> findAll();
}
