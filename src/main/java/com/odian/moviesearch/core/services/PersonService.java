package com.odian.moviesearch.core.services;


import com.odian.moviesearch.core.PersonDao;
import com.odian.moviesearch.core.exceptions.NotFoundException;
import com.odian.moviesearch.core.model.Person;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class PersonService {

    private final PersonDao personDao;


    public Person create (Person person) {
        return personDao.create(person);
    }

    public Person findById (Long id) {
        return personDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with this id not found"));
    }

    public Person update (Person person) {
        if (Objects.requireNonNull(person).getId() == null) {
            throw new IllegalArgumentException("Person must have an id");
        }
        return personDao.update(person);
    }
}
