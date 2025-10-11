package be.kdg.keepdishesgoing.restaurant.adapter.out.dish;

import be.kdg.keepdishesgoing.restaurant.domain.DishVersion;
import be.kdg.keepdishesgoing.restaurant.domain.DishVersionId;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.CreateDishVersionPort;
import be.kdg.keepdishesgoing.restaurant.port.out.dish.UpdateDishVersionPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class DishVersionJpaAdapter implements CreateDishVersionPort, UpdateDishVersionPort {

    private static final Logger logger = LoggerFactory.getLogger(DishVersionJpaAdapter.class);
    private final DishVersionJpaRepository dishVersionJpaRepository;

    public DishVersionJpaAdapter(DishVersionJpaRepository dishVersionJpaRepository) {
        this.dishVersionJpaRepository = dishVersionJpaRepository;
    }

    @Override
    @Transactional
    public DishVersion save(DishVersion dishVersion) {
        DishVersionJpaEntity dishVersionJpaEntity = mapToEntity(dishVersion);
        DishVersionJpaEntity saved = dishVersionJpaRepository.save(dishVersionJpaEntity);
        return mapToDomain(saved);
    }


    @Override
    @Transactional
    public DishVersion update(DishVersion updatedDishVersion) {
        DishVersionJpaEntity existingEntity = dishVersionJpaRepository.findById(
                updatedDishVersion.getDishVersionId().uuid())
                .orElseThrow(() -> new EntityNotFoundException("DishVersion not found: " +
                        updatedDishVersion.getDishVersionId().uuid()));

        logger.info("Update DishVersion {}", updatedDishVersion);

        // Only update changed fields
        if (updatedDishVersion.getNameOfDish() != null) {
            existingEntity.setNameOfDish(updatedDishVersion.getNameOfDish());
        }

        if (updatedDishVersion.getDescription() != null) {
            existingEntity.setDescription(updatedDishVersion.getDescription());
        }

        if (updatedDishVersion.getPrice() != 0.00) {
            existingEntity.setPrice(updatedDishVersion.getPrice());
        }

        if (updatedDishVersion.getPicture() != null) {
            existingEntity.setPicture(updatedDishVersion.getPicture());
        }


        if (updatedDishVersion.getPreparationTime() != null) {
            existingEntity.setPreparationTime(updatedDishVersion.getPreparationTime());
        }

        if (updatedDishVersion.getFoodTag() != null) {
            existingEntity.setFoodTag(updatedDishVersion.getFoodTag());
        }

        if (updatedDishVersion.getTypeOfDish() != null) {
            existingEntity.setDishType(updatedDishVersion.getTypeOfDish());
        }

        DishVersionJpaEntity savedEntity = dishVersionJpaRepository.save(existingEntity);
        return mapToDomain(savedEntity);
    }


    private DishVersionJpaEntity mapToEntity(DishVersion version) {
        if (version == null) return null;
        return new DishVersionJpaEntity(
                version.getDishVersionId().uuid(),
                version.getNameOfDish(),
                version.getDescription(),
                version.getPrice(),
                version.getPicture(),
                version.getPreparationTime(),
                version.getFoodTag(),
                version.getTypeOfDish()
        );
    }

    private DishVersion mapToDomain(DishVersionJpaEntity entity) {
        if (entity == null) return null;
        return new DishVersion(
                new DishVersionId(entity.getDishVersionId()),
                entity.getNameOfDish(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getPicture(),
                entity.getPreparationTime(),
                entity.getFoodTag(),
                entity.getDishType()
        );
    }


}
