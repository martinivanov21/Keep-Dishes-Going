package be.kdg.keepdishesgoing.restaurant.adapter.out.owner;

import be.kdg.keepdishesgoing.restaurant.domain.Owner;
import be.kdg.keepdishesgoing.restaurant.domain.OwnerId;
import be.kdg.keepdishesgoing.restaurant.port.in.FindAllOwnerPort;
import be.kdg.keepdishesgoing.restaurant.port.out.owner.LoadOwnerPort;
import be.kdg.keepdishesgoing.restaurant.port.out.owner.SaveOwnerPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class OwnerJpaAdapter implements LoadOwnerPort, FindAllOwnerPort, SaveOwnerPort {

    private final Logger log = LoggerFactory.getLogger(OwnerJpaAdapter.class);
    private final OwnerJpaRepository ownerJpaRepository;

    public OwnerJpaAdapter(OwnerJpaRepository ownerJpaRepository) {
        this.ownerJpaRepository = ownerJpaRepository;
    }

    @Override
    public Optional<Owner> loadOwnerById(OwnerId ownerId) {
        return ownerJpaRepository.findById(ownerId.uuid())
                .map(this::mapToDomain);
    }

    @Override
    public List<Owner> findAllOwners() {
        return ownerJpaRepository.findAll().stream().map(this::mapToDomain).toList();
    }

    @Override
    public Optional<Owner> findByEmail(String email) {
        return  ownerJpaRepository.findByEmail(email);
    }

    private OwnerJpaEntity mapToEntity(Owner owner) {
        return new OwnerJpaEntity(
                owner.getOwnerId().uuid(),
                owner.getFirstName(),
                owner.getLastName(),
                owner.getEmail()
        );
    }

    private Owner mapToDomain(OwnerJpaEntity entity) {
        return new Owner(
                new OwnerId(entity.getOwnerId()),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail()
        );
    }

    @Override
    public Owner save(Owner owner) {
        OwnerJpaEntity entity = mapToEntity(owner);
        OwnerJpaEntity saved = ownerJpaRepository.save(entity);
        return mapToDomain(saved);
    }

    @Transactional
    public void ensureOwnerExists(UUID ownerUuid, String email) {
        if (ownerJpaRepository.existsById(ownerUuid)) {
            ownerJpaRepository.findById(ownerUuid).ifPresent(e -> {
                if ((e.getEmail() == null || e.getEmail().isBlank()) && email != null) {
                    e.setEmail(email);
                    ownerJpaRepository.save(e);
                }
            });
        }
    }
}
