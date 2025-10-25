package be.kdg.keepdishesgoing.customerOrder.port.out;


import be.kdg.keepdishesgoing.customerOrder.domain.Dish;

public interface SaveDishPort {
    Dish saveDish(Dish dish);
}
