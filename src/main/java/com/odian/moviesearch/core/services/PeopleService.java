package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.Person;
import com.odian.moviesearch.core.model.utils.Pageable;

public interface PeopleService {
    Person create (Person person);
    Person findById (Long id);
    PagedResponse<Person> findAll (Pageable pageable);
}
