package io.github.leonnatale.ultest.controller;

import io.github.leonnatale.ultest.dto.person.PersonDTO;
import io.github.leonnatale.ultest.dto.person.PersonViewDTO;
import io.github.leonnatale.ultest.group.OnCreate;
import io.github.leonnatale.ultest.model.person.Person;
import io.github.leonnatale.ultest.repositories.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PersonRepository personRepository;

    public PeopleController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<PersonViewDTO> getAuthenticatedPerson(@AuthenticationPrincipal Person me) {
        return ResponseEntity.ok(me.asView());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonViewDTO> getPerson(@PathVariable Long id) {
        var foundPerson = personRepository.findById(id);
        if (foundPerson.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(foundPerson.get().asView());
    }

    @PostMapping
    public ResponseEntity<PersonViewDTO> createPerson(
            @Validated(OnCreate.class) @RequestBody PersonDTO personDTO
    ) {
        var newPerson = Person.from(personDTO);
        PersonViewDTO view = personRepository.save(newPerson).asView();
        return ResponseEntity.ok(view);
    }
}
