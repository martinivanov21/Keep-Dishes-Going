package be.kdg.keepdishesgoing.restaurant.core.dish;

import be.kdg.keepdishesgoing.restaurant.domain.OwnerId;
import be.kdg.keepdishesgoing.restaurant.domain.Restaurant;
import be.kdg.keepdishesgoing.restaurant.domain.WorkingHour;
import be.kdg.keepdishesgoing.restaurant.port.in.UpdateRestaurantCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.UpdateRestaurantUseCase;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.LoadRestaurantPort;
import be.kdg.keepdishesgoing.restaurant.port.out.restaurant.UpdateRestaurantPort;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class UpdateRestaurantUseCaseImpl implements UpdateRestaurantUseCase {
    private final LoadRestaurantPort loadRestaurantPort;
    private final UpdateRestaurantPort updateRestaurantPort;


    public UpdateRestaurantUseCaseImpl(LoadRestaurantPort loadRestaurantPort, UpdateRestaurantPort updateRestaurantPort) {
        this.loadRestaurantPort = loadRestaurantPort;
        this.updateRestaurantPort = updateRestaurantPort;
    }

    @Override
    public Restaurant update(UpdateRestaurantCommand command) {
        var existing = loadRestaurantPort.loadBy(OwnerId.of(command.ownerId().toString()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<WorkingHour> hours = new ArrayList<>();
        if (command.workingHours() != null) {
            command.workingHours().forEach((day, interval) -> {
                LocalTime open  = LocalTime.parse(interval.openHour());
                LocalTime close = LocalTime.parse(interval.closeHour());
                if (!open.isBefore(close)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Opening time must be before closing time for " + day);
                }
                hours.add(new WorkingHour(day, open, close));
            });
            hours.sort(Comparator.comparing(WorkingHour::getDayOfWeek));
        }

        var merged = new Restaurant(
                existing.getRestaurantId(),
                command.nameOfRestaurant() != null ? command.nameOfRestaurant() : existing.getNameOfRestaurant(),
                command.cuisine() != null ? command.cuisine() : existing.getCuisine(),
                command.openingStatus() != null ? command.openingStatus() : existing.getOpeningStatus(),
                command.defaultPreparationTime() != null ? command.defaultPreparationTime() : existing.getDefaultPreparationTime(),
                command.contactEmail() != null ? command.contactEmail() : existing.getContactEmail(),
                command.picture() != null ? command.picture() : existing.getPicture(),
                existing.getAddressId(),
                existing.getOwnerId(),
                existing.getMenuId(),
                hours
        );

        return updateRestaurantPort.update(merged);
    }
}
