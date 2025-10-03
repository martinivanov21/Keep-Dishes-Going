package be.kdg.keepdishesgoing.restaurant.adapter.out.owner;

import be.kdg.keepdishesgoing.restaurant.domain.Owner;
import be.kdg.keepdishesgoing.restaurant.domain.OwnerId;
import be.kdg.keepdishesgoing.restaurant.port.out.owner.LoadOwnerPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OwnerJpaAdapter implements LoadOwnerPort {
    @Override
    public Optional<Owner> loadOwnerById(OwnerId ownerId) {
        return Optional.empty();
    }
}
