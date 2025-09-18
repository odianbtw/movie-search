package com.odian.moviesearch.core.application.port.out;

import com.odian.moviesearch.core.domain.model.Person;

import java.util.Optional;
import java.util.UUID;

public interface PeopleRepository {
    Person save (Person person);
    Optional<Person> findById (UUID id);
}
