package be.kdg.keepdishesgoing.customerOrder.port.out;

import be.kdg.keepdishesgoing.customerOrder.domain.DishId;

public interface DeleteDishPort {
    void deleteDish(DishId dishId);

}
