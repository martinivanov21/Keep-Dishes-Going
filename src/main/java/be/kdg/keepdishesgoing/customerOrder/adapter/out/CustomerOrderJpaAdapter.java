package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.adapter.mapper.CustomerOrderMapper;
import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrder;
import be.kdg.keepdishesgoing.customerOrder.port.out.DeleteCustomerOrderPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadCustomerOrderPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveCustomerOrderPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.UpdateCustomerOrderPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerOrderJpaAdapter implements DeleteCustomerOrderPort, LoadCustomerOrderPort, UpdateCustomerOrderPort, SaveCustomerOrderPort {

    private final CustomerOrderMapper customerOrderMapper;
    private final CustomerOrderJpaRepository customerOrderJpaRepository;

    public CustomerOrderJpaAdapter(CustomerOrderMapper customerOrderMapper, CustomerOrderJpaRepository customerOrderJpaRepository) {
        this.customerOrderMapper = customerOrderMapper;
        this.customerOrderJpaRepository = customerOrderJpaRepository;
    }

    @Override
    public List<CustomerOrder> loadAll() {
        return customerOrderJpaRepository.findAll().stream().map(customerOrderMapper::toDomain).toList();
    }

    @Override
    public CustomerOrder save(CustomerOrder customerOrder) {
        CustomerOrderJpaEntity customerOrderJpaEntity =  customerOrderMapper.toEntity(customerOrder);
        CustomerOrderJpaEntity saved = customerOrderJpaRepository.save(customerOrderJpaEntity);

        return customerOrderMapper.toDomain(saved);
    }

    @Override
    public CustomerOrder update(CustomerOrder customerOrder) {
        CustomerOrderJpaEntity customerOrderJpaEntity =  customerOrderMapper.toEntity(customerOrder);
        CustomerOrderJpaEntity updated = customerOrderJpaRepository.save(customerOrderJpaEntity);

        return customerOrderMapper.toDomain(updated);
    }
}
