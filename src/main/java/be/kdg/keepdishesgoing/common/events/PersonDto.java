package be.kdg.keepdishesgoing.common.events;

import be.kdg.keepdishesgoing.common.domain.Role;

import java.util.UUID;

public record PersonDto(String personId, String username, String email, String role) {
}
