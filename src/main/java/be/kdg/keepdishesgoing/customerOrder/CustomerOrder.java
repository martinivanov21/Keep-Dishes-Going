package be.kdg.keepdishesgoing.customerOrder;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerOrder {
    private UUID customerOrderId;
    private double estimateTime;
    private double totalPrice;
    private LocalDateTime submittedTime;
    private OrderStatus orderStatus;
}
