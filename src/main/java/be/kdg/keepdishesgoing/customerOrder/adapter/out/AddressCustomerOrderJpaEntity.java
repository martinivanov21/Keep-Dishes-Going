package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "address_customer_order", schema = "kdg_customerOrder")
public class AddressCustomerOrderJpaEntity {

    @Id
    @Column(name = "address_id")
    private UUID addressId;
    private String street;
    @Column(name = "street_name")
    private int number;
    private String city;
    private String postalCode;
    private String country;

    public AddressCustomerOrderJpaEntity() {
    }

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
