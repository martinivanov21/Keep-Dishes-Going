package be.kdg.keepdishesgoing.restaurant.core;

import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.port.in.FindAllRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.LoadRestaurantPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllRestaurantUseCaseImpl implements FindAllRestaurantPort {

    private final LoadRestaurantPort loadRestaurantPort;

    @Override
    public List<Restaurant> findAll() {
        return this.loadRestaurantPort.loadAll();
    }


    public FindAllRestaurantUseCaseImpl(LoadRestaurantPort loadRestaurantPort) {
        this.loadRestaurantPort = loadRestaurantPort;
    }
}
