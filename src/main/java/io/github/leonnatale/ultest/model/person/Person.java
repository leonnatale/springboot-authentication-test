package io.github.leonnatale.ultest.model.person;

import io.github.leonnatale.ultest.dto.person.PersonDTO;
import io.github.leonnatale.ultest.dto.person.PersonViewDTO;
import io.github.leonnatale.ultest.group.OnCreate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public static Person from(@Validated(OnCreate.class) PersonDTO personDTO) {
        return new Person(
                null,
                personDTO.name(),
                personDTO.birthdate(),
                RoleEnum.CUSTOMER
        );
    }

    public PersonViewDTO asView() {
        return new PersonViewDTO(
                id,
                name,
                birthdate,
                role
        );
    }
}
