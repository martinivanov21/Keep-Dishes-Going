package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.adapter.in.request.CreateOwnerRequest;
import be.kdg.keepdishesgoing.restaurant.adapter.in.response.OwnerDto;
import be.kdg.keepdishesgoing.restaurant.domain.Owner;
import be.kdg.keepdishesgoing.restaurant.domain.OwnerId;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateOwnerCommand;
import be.kdg.keepdishesgoing.restaurant.port.in.CreateOwnerUseCase;
import be.kdg.keepdishesgoing.restaurant.port.in.FindAllOwnerPort;
import be.kdg.keepdishesgoing.restaurant.port.out.owner.LoadOwnerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/owners")
@PreAuthorize("hasRole('OWNER')")
public class OwnerController {

    private final CreateOwnerUseCase createOwnerUseCase;
    private final FindAllOwnerPort findAllOwnerPort;
    private final LoadOwnerPort loadOwnerPort;



    public OwnerController(CreateOwnerUseCase createOwnerUseCase, FindAllOwnerPort findAllOwnerPort, LoadOwnerPort loadOwnerPort) {
        this.createOwnerUseCase = createOwnerUseCase;
        this.findAllOwnerPort = findAllOwnerPort;
        this.loadOwnerPort = loadOwnerPort;
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

    @GetMapping("/{ownerId}")
    @PreAuthorize("hasRole('OWNER') and #ownerId.toString() == authentication.token.claims['owner_id']")
    public ResponseEntity<OwnerDto> getOwner(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID ownerId) {

        var owner = loadOwnerPort.loadOwnerById(new OwnerId(ownerId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found"));

        return ResponseEntity.ok(new OwnerDto(
                owner.getOwnerId().uuid(),
                owner.getFirstName(),
                owner.getLastName(),
                owner.getEmail()
        ));
    }

}
