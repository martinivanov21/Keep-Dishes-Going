package be.kdg.keepdishesgoing.customerOrder.port.out;

import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrder;
import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrderId;

import java.util.List;
import java.util.Optional;

public interface LoadCustomerOrderPort {
    List<CustomerOrder> loadAll();
    List<CustomerOrder> loadByCustomerEmail(String email);
    Optional<CustomerOrder> loadById(CustomerOrderId orderId);
}
