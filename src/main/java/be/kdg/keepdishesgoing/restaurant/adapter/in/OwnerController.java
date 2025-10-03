package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.response.OwnerDto;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateOwnerUseCase;
import be.kdg.keepdishesgoing.restaurant.port.in.FindAllOwnerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class OwnerController {

    private final CreateOwnerUseCase createOwnerUseCase;
    private final FindAllOwnerPort findAllOwnerPort;


    public OwnerController(CreateOwnerUseCase createOwnerUseCase, FindAllOwnerPort findAllOwnerPort) {
        this.createOwnerUseCase = createOwnerUseCase;
        this.findAllOwnerPort = findAllOwnerPort;
    }

//    public ResponseEntity<OwnerDto> createOwner(@RequestBody CreateOwnerRequest request)


    public ResponseEntity<List<OwnerDto>> findAllOwners() {
        List<OwnerDto> ownerDtos = this.findAllOwnerPort.findAllOwners().stream()
                .map(owner -> new OwnerDto(owner.getFirstName(),owner.getLastName(),owner.getEmail()))
                .toList();
        return ResponseEntity.ok(ownerDtos);
    }

}
