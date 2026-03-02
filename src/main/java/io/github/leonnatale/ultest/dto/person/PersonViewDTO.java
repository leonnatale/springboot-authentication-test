package io.github.leonnatale.ultest.dto.person;

import io.github.leonnatale.ultest.model.person.RoleEnum;

import java.time.LocalDate;

public record PersonViewDTO(
        Long id,
        String name,
        LocalDate birthdate,
        RoleEnum role
) {}
