package com.odian.moviesearch.core;

import com.odian.moviesearch.core.model.Person;

import java.util.Optional;

public interface PersonDao {
    Person create(Person person);
    Optional<Person> findById(Long id);
    Person update(Person person);
}
