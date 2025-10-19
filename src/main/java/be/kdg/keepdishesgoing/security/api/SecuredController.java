package be.kdg.keepdishesgoing.security.api;

import be.kdg.keepdishesgoing.security.api.dtos.SecuredMessageDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('owner')")
    public String testOwnerAccess(@AuthenticationPrincipal Jwt jwt) {
        return "Hello " + jwt.getClaimAsString("preferred_username") + "! You have the owner role.";
    }

    @GetMapping("/public")
    public String publicAccess() {
        return "This is open to everyone.";
    }

}
