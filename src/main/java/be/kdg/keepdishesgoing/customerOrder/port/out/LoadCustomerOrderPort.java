package be.kdg.keepdishesgoing.customerOrder.port.out;

import be.kdg.keepdishesgoing.common.domain.PersonId;
import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrder;

import java.util.List;
import java.util.Optional;

public interface LoadCustomerOrderPort {

    Optional<CustomerOrder> load(PersonId owner);

    List<CustomerOrder> loadAll();
}
