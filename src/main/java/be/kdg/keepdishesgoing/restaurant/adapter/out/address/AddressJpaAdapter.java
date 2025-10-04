package be.kdg.keepdishesgoing.restaurant.adapter.out.address;

import org.springframework.stereotype.Repository;

@Repository
public class AddressJpaAdapter {

    private final AddressJpaRepository addresses;

    public AddressJpaAdapter(AddressJpaRepository addresses) {
        this.addresses = addresses;
    }
}
