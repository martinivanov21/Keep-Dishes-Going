package be.kdg.keepdishesgoing.restaurant.adapter.out.address;

import be.kdg.keepdishesgoing.restaurant.domain.Address;
import be.kdg.keepdishesgoing.restaurant.domain.AddressId;
import be.kdg.keepdishesgoing.restaurant.port.out.address.LoadAddressPort;
import be.kdg.keepdishesgoing.restaurant.port.out.address.SaveAddressPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressJpaAdapter implements SaveAddressPort, LoadAddressPort {

    private final Logger log = LoggerFactory.getLogger(AddressJpaAdapter.class);
    private final AddressJpaRepository addressJpaRepository;

    public AddressJpaAdapter(AddressJpaRepository addressJpaRepository) {
        this.addressJpaRepository = addressJpaRepository;
    }


    @Override
    public Optional<Address> loadById(AddressId addressId) {
        return addressJpaRepository.findById(addressId.uuid())
                .map(this::mapToDomain);
    }

    @Override
    @Transactional
    public Address save(Address address) {
        AddressJpaEntity entity = mapToEntity(address);
        addressJpaRepository.save(entity);
        return address;
    }
    
    public List<Address> loadAll() {
        return addressJpaRepository.findAll().stream().map(this::mapToDomain).toList();
    }

    private AddressJpaEntity mapToEntity(Address address) {
        return new AddressJpaEntity(
                address.getAddressId().uuid(),
                address.getStreet(),
                address.getNumber(),
                address.getPostalCode(),
                address.getCity(),
                address.getCountry()
        );
    }
    private Address mapToDomain(AddressJpaEntity entity) {
        return new Address(
                new AddressId(entity.getAddressId()),
                entity.getStreet(),
                entity.getNumber(),
                entity.getPostalCode(),
                entity.getCity(),
                entity.getCountry()
        );
    }

}
