package io.github.leonnatale.ultest.repositories;

import io.github.leonnatale.ultest.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
