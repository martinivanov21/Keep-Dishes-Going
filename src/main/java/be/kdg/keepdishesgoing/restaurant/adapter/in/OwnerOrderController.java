package be.kdg.keepdishesgoing.restaurant.adapter.in;

import be.kdg.keepdishesgoing.restaurant.port.in.dish.OwnerOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/owner/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OwnerOrderController {

    private final OwnerOrderUseCase ownerOrderUseCase;


    public OwnerOrderController(OwnerOrderUseCase ownerOrderUseCase) {
        this.ownerOrderUseCase = ownerOrderUseCase;
    }

    @PutMapping("/{orderId}/accept")
    @PreAuthorize("#ownerId.toString() == (authentication.token.claims['owner_id'] ?: authentication.token.subject)")
    public ResponseEntity<Void> accept(
            @RequestHeader("X-Owner-Id") UUID ownerId,
            @PathVariable UUID orderId,
            @RequestParam int finalEstimatedTime
    ) {

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{orderId}/decline")
    @PreAuthorize("#ownerId.toString() == (authentication.token.claims['owner_id'] ?: authentication.token.subject)")
    public ResponseEntity<Void> decline(
            @RequestHeader("X-Owner-Id") UUID ownerId,
            @PathVariable UUID orderId,
            @RequestParam(required = false) String reason
    ) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{orderId}/items/{dishId}/quantity")
    @PreAuthorize("#ownerId.toString() == (authentication.token.claims['owner_id'] ?: authentication.token.subject)")
    public ResponseEntity<Void> changeQty(
            @RequestHeader("X-Owner-Id") UUID ownerId,
            @PathVariable UUID orderId,
            @PathVariable UUID dishId,
            @RequestParam int quantity
    ) {
        return ResponseEntity.noContent().build();
    }
}
