package be.kdg.keepdishesgoing.restaurant.adapter;

import be.kdg.keepdishesgoing.restaurant.domain.Owner;
import be.kdg.keepdishesgoing.restaurant.domain.OwnerId;
import be.kdg.keepdishesgoing.restaurant.port.in.CurrentOwnerPort;
import be.kdg.keepdishesgoing.restaurant.port.in.FindAllOwnerPort;
import be.kdg.keepdishesgoing.restaurant.port.out.owner.LoadOwnerPort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityCurrentOwnerAdapter implements CurrentOwnerPort {

    private final FindAllOwnerPort findAllOwnerPort;

    public SecurityCurrentOwnerAdapter(FindAllOwnerPort findAllOwnerPort) {
        this.findAllOwnerPort = findAllOwnerPort;
    }

    @Override
    public OwnerId currentOwnerId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof JwtAuthenticationToken token)) {
            throw new AccessDeniedException("Unauthenticated");
        }

        Jwt jwt = token.getToken();

        String ownerIdStr = jwt.getClaimAsString("owner_id");
        if (ownerIdStr != null && !ownerIdStr.isBlank()) {
            try { return new OwnerId(UUID.fromString(ownerIdStr)); }
            catch (IllegalArgumentException ex) {
                throw new AccessDeniedException("Invalid owner_id claim (not UUID).");
            }
        }
        String email = jwt.getClaimAsString("email");
        if (email == null || email.isBlank()) {
            throw new AccessDeniedException("No owner_id or email in token; configure a protocol mapper.");
        }
        return findAllOwnerPort.findByEmail(email)
                .map(Owner::getOwnerId)
                .orElseThrow(() -> new AccessDeniedException(
                        "No Owner found for email: " + email + ". Seed or map your owners."));
    }

}
