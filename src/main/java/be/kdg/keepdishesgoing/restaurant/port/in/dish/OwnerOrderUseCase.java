package be.kdg.keepdishesgoing.restaurant.port.in.dish;

import java.util.UUID;

public interface OwnerOrderUseCase {
    void acceptOrder(AcceptOrderCommand command);
    void declineOrder(DeclineOrderCommand command);
    void changeItemQuantity(ChangeItemQuantityCommand command);
}
