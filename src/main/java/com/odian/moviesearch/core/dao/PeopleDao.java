package com.odian.moviesearch.core.dao;

import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.Person;
import com.odian.moviesearch.core.model.utils.Pageable;

import java.util.Optional;

public interface PeopleDao {
    Person create (Person person);
    Optional<Person> findById (Long id);
    PagedResponse<Person> findAll (Pageable pageable);
}
