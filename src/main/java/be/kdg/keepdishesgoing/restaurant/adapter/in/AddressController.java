package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateAddressRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.AddressDto;
import be.kdg.keepdishesgoing.restaurant.domain.Address;
import be.kdg.keepdishesgoing.restaurant.domain.AddressId;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateAddressCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateAddressUseCase;
import be.kdg.keepdishesgoing.restaurant.port.in.FindAllAddressUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final CreateAddressUseCase createAddressUseCase;
    private final FindAllAddressUseCase findAllAddressUseCase;


    public AddressController(CreateAddressUseCase createAddressUseCase, FindAllAddressUseCase findAllAddressUseCase) {
        this.createAddressUseCase = createAddressUseCase;
        this.findAllAddressUseCase = findAllAddressUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<AddressDto> createAddress(@RequestBody CreateAddressRequest request) {

        var addressId = AddressId.create();

        var command = new CreateAddressCommand(
                new Address(
                        addressId,
                        request.street(),
                        request.number(),
                        request.postalCode(),
                        request.city(),
                        request.country()
                        )
        );

        Address newAddress = createAddressUseCase.createAddress(command);

        return ResponseEntity.ok(
                new AddressDto(
                        newAddress.getAddressId().uuid(),
                        newAddress.getStreet(),
                        newAddress.getNumber(),
                        newAddress.getPostalCode(),
                        newAddress.getCity(),
                        newAddress.getCountry()
                ));
    }

    @GetMapping()
    public ResponseEntity<List<AddressDto>> getAllAddresses() {

        List<AddressDto> addressDtos = findAllAddressUseCase.findAll().stream()
                .map(
                        address -> new AddressDto(
                                address.getAddressId().uuid(),
                                address.getStreet(),
                                address.getNumber(),
                                address.getPostalCode(),
                                address.getCity(),
                                address.getCountry()
                        )
                ).toList();

        return ResponseEntity.ok(addressDtos);
    }
}
