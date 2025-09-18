package com.odian.moviesearch.core.application.port.in;

import com.odian.moviesearch.core.domain.model.Person;

import java.util.UUID;

public interface PeopleService {
    Person save (Person person);
    Person findById (UUID id);
}
