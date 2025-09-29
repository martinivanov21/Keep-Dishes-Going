package be.kdg.keepdishesgoing.common.events;

import be.kdg.keepdishesgoing.common.domain.Person;

public interface CreatePersonUseCase {
    Person createPerson(CreatePersonCommand createPersonCommand);
}
