package be.kdg.keepdishesgoing.common.events;

import be.kdg.keepdishesgoing.common.domain.Role;

public record CreatePersonCommand(String username, String password, String email, Role role) {
}
