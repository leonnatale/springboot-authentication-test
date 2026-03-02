package io.github.leonnatale.ultest.dto.person;

import io.github.leonnatale.ultest.group.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PersonDTO(
        @NotBlank(groups = OnCreate.class)
        String name,
        @NotNull(groups = OnCreate.class)
        LocalDate birthdate
) {}
