package be.kdg.keepdishesgoing.common.domain;

import java.util.UUID;

public record PersonId(UUID uuid) {

    public PersonId of(UUID uuid) {
        return new PersonId(uuid);
    }

    public PersonId create() {
        return new PersonId(UUID.randomUUID());
    }
}
