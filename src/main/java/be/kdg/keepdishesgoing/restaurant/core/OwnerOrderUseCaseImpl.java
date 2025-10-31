package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.port.in.dish.AcceptOrderCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.ChangeItemQuantityCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.DeclineOrderCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.dish.OwnerOrderUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.ChangeOrderItemQuantityPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OwnerOrderUseCaseImpl implements OwnerOrderUseCase {
    @Override
    public void acceptOrder(AcceptOrderCommand command) {

    }

    @Override
    public void declineOrder(DeclineOrderCommand command) {

    }

    @Override
    public void changeItemQuantity(ChangeItemQuantityCommand command) {

    }

//    private static final Logger log = LoggerFactory.getLogger(OwnerOrderUseCaseImpl.class);
//    private final AcceptCustomerOrderPort acceptPort;
//    private final DeclineCustomerOrderPort declinePort;
//    private final ChangeOrderItemQuantityPort changeQtyPort;
//    private final LoadRestaurantIdForOrderPort loadRestaurantIdForOrderPort;      // to verify ownership
//    private final VerifyOwnerOwnsRestaurantPort verifyOwnerOwnsRestaurantPort;
//
//    public OwnerOrderUseCaseImpl(AcceptCustomerOrderPort acceptPort, DeclineCustomerOrderPort declinePort, ChangeOrderItemQuantityPort changeQtyPort, LoadRestaurantIdForOrderPort loadRestaurantIdForOrderPort, VerifyOwnerOwnsRestaurantPort verifyOwnerOwnsRestaurantPort) {
//        this.acceptPort = acceptPort;
//        this.declinePort = declinePort;
//        this.changeQtyPort = changeQtyPort;
//        this.loadRestaurantIdForOrderPort = loadRestaurantIdForOrderPort;
//        this.verifyOwnerOwnsRestaurantPort = verifyOwnerOwnsRestaurantPort;
//    }
//
//    @Override
//    public void acceptOrder(AcceptOrderCommand command) {
//
//    }
//
//    @Override
//    public void declineOrder(DeclineOrderCommand command) {
//
//    }
//
//    @Override
//    @Transactional
//    public void changeItemQuantity(ChangeItemQuantityCommand command) {
//        ensureOwnership(command.ownerId(), command.orderId());
//        log.info("Owner {} changes qty for order {} dish {} -> {}",
//                command.ownerId(), command.orderId(), command.dishId(), command.quantity());
//        changeQtyPort.changeQuantity(command);
//    }
}
