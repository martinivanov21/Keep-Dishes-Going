//package be.kdg.keepdishesgoing.common.events;
//
//import be.kdg.keepdishesgoing.common.domain.Person;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/person")
//public class PersonController {
//
//    private final CreatePersonUseCase createPersonUseCase;
//
//    public PersonController(CreatePersonUseCase createPersonUseCase) {
//        this.createPersonUseCase = createPersonUseCase;
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<PersonDto> createPerson(@RequestBody CreatePersonRequest request) {
//        var command = new CreatePersonCommand(
//                request.username(),
//                request.password(),
//                request.email(),
//                request.role()
//        );
//
//        Person person = createPersonUseCase.createPerson(command);
//
//        return ResponseEntity.ok(new PersonDto(
//                person.getPersonId().toString(),
//                person.getUsername(),
//                person.getEmail(),
//                person.getRole().name()
//        ));
//    }
//}
