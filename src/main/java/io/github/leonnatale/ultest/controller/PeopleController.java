package io.github.leonnatale.ultest.controller;

import io.github.leonnatale.ultest.dto.person.PersonDTO;
import io.github.leonnatale.ultest.dto.person.PersonViewDTO;
import io.github.leonnatale.ultest.group.OnCreate;
import io.github.leonnatale.ultest.model.person.Person;
import io.github.leonnatale.ultest.repositories.PersonRepository;
import io.github.leonnatale.ultest.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PersonRepository personRepository;
    private final JwtService jwtService;

    public PeopleController(
            PersonRepository personRepository,
            JwtService jwtService
    ) {
        this.personRepository = personRepository;
        this.jwtService = jwtService;
    }

    @GetMapping("/me")
    public ResponseEntity<PersonViewDTO> getAuthenticatedPerson(@AuthenticationPrincipal Person me) {
        return ResponseEntity.ok(me.asView());
    }

    @GetMapping("/me/token")
    public ResponseEntity<String> getAuthenticationToken(@AuthenticationPrincipal Person me) {
        return ResponseEntity.ok(jwtService.generate(me.getId()));
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
