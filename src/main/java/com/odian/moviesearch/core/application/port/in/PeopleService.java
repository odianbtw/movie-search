package com.odian.moviesearch.core.application.port.in;


import com.odian.moviesearch.core.domain.model.Person;

public interface PeopleService {
    Person create (Person person);
    Person findById (Long id);
}
