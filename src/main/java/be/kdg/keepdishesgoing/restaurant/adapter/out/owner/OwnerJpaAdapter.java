package be.kdg.keepdishesgoing.restaurant.adapter.out.owner;

import be.kdg.keepdishesgoing.restaurant.domain.Owner;
import be.kdg.keepdishesgoing.restaurant.domain.OwnerId;
import be.kdg.keepdishesgoing.restaurant.port.in.FindAllOwnerPort;
import be.kdg.keepdishesgoing.restaurant.port.out.owner.LoadOwnerPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OwnerJpaAdapter implements LoadOwnerPort, FindAllOwnerPort {
    @Override
    public Optional<Owner> loadOwnerById(OwnerId ownerId) {
        return Optional.empty();
    }

    @Override
    public List<Owner> findAllOwners() {
        return List.of();
    }
}
