//package be.kdg.keepdishesgoing.customerOrder.core;
//
//import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrder;
//import be.kdg.keepdishesgoing.customerOrder.port.in.FindAllCustomerOrderPort;
//import be.kdg.keepdishesgoing.customerOrder.port.out.LoadCustomerOrderPort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FindAllCustomerOrderUseCaseImpl implements FindAllCustomerOrderPort {
//
//    private final LoadCustomerOrderPort loadCustomerOrderPort;
//
//    public FindAllCustomerOrderUseCaseImpl(LoadCustomerOrderPort loadCustomerOrderPort) {
//        this.loadCustomerOrderPort = loadCustomerOrderPort;
//    }
//
//    @Override
//    public List<CustomerOrder> findAll() {
//        return this.loadCustomerOrderPort.loadAll();
//    }
//}
