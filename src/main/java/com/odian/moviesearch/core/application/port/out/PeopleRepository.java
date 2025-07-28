package com.odian.moviesearch.core.application.port.out;

import com.odian.moviesearch.core.domain.model.Person;

import java.util.Optional;

public interface PeopleRepository {
    Person create (Person person);
    Optional<Person> findById (Long id);
}
