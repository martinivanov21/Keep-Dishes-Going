package be.kdg.keepdishesgoing.customerOrder.adapter.in;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.request.UpdateStatusRequest;
import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.CustomerOrderDto;
import be.kdg.keepdishesgoing.customerOrder.adapter.mapper.CustomerOrderMapper;
import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrder;
import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrderId;
import be.kdg.keepdishesgoing.customerOrder.domain.OrderStatus;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadCustomerOrderPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.UpdateCustomerOrderPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer-order/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerOrderController {

    private final LoadCustomerOrderPort loadCustomerOrderPort;
    private final UpdateCustomerOrderPort updateCustomerOrderPort;
    private final CustomerOrderMapper customerOrderMapper;

    public CustomerOrderController(LoadCustomerOrderPort loadCustomerOrderPort, UpdateCustomerOrderPort updateCustomerOrderPort, CustomerOrderMapper customerOrderMapper) {
        this.loadCustomerOrderPort = loadCustomerOrderPort;
        this.updateCustomerOrderPort = updateCustomerOrderPort;
        this.customerOrderMapper = customerOrderMapper;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<CustomerOrderDto> getById(@PathVariable UUID orderId) {
        CustomerOrder order = loadCustomerOrderPort.loadById(CustomerOrderId.of(orderId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(customerOrderMapper.toDto(order));
    }

    @GetMapping
    public ResponseEntity<List<CustomerOrderDto>> list(
            @RequestParam(required = false) String email) {
        List<CustomerOrder> orders = (email == null || email.isBlank())
                ? loadCustomerOrderPort.loadAll()
                : loadCustomerOrderPort.loadByCustomerEmail(email);

        List<CustomerOrderDto> dtos = orders.stream()
                .map(customerOrderMapper::toDto)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID orderId,
                                             @RequestBody UpdateStatusRequest body) {
        CustomerOrder order = loadCustomerOrderPort.loadById(new CustomerOrderId(orderId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        try {
            OrderStatus newStatus = OrderStatus.valueOf(body.status());
            order.updateStatus(newStatus);
            updateCustomerOrderPort.update(order);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status: " + body.status());
        }
    }
}
