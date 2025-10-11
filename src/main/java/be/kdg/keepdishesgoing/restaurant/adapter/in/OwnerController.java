package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateOwnerRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.OwnerDto;
import be.kdg.keepdishesgoing.restaurant.domain.Owner;
import be.kdg.keepdishesgoing.restaurant.domain.OwnerId;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateOwnerCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateOwnerUseCase;
import be.kdg.keepdishesgoing.restaurant.port.in.FindAllOwnerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final CreateOwnerUseCase createOwnerUseCase;
    private final FindAllOwnerPort findAllOwnerPort;


    public OwnerController(CreateOwnerUseCase createOwnerUseCase, FindAllOwnerPort findAllOwnerPort) {
        this.createOwnerUseCase = createOwnerUseCase;
        this.findAllOwnerPort = findAllOwnerPort;
    }

    @PostMapping("/create")
    public ResponseEntity<OwnerDto> createOwner(@RequestBody CreateOwnerRequest request) {

        var ownerId = OwnerId.create();

        var command = new CreateOwnerCommand(
                new Owner( ownerId, request.firstName(), request.lastName(), request.email())
        );

        Owner newOwner = createOwnerUseCase.createOwner(command);

        return ResponseEntity.ok(new OwnerDto(
                newOwner.getOwnerId().uuid(),
                newOwner.getFirstName(),
                newOwner.getLastName(),
                newOwner.getEmail()
        ));
    }

    @GetMapping()
    public ResponseEntity<List<OwnerDto>> findAllOwners() {
        List<OwnerDto> ownerDtos = this.findAllOwnerPort.findAllOwners().stream()
                .map(owner -> new OwnerDto(owner.getOwnerId().uuid(),
                        owner.getFirstName(),owner.getLastName(),owner.getEmail()))
                .toList();
        return ResponseEntity.ok(ownerDtos);
    }

}
