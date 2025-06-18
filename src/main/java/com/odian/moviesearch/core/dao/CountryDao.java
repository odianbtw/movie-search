package com.odian.moviesearch.core.dao;

import com.odian.moviesearch.core.model.Country;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CountryDao {
    List<Country> findAll ();
    Optional<Country> findById(Integer id);
    Set<Country> findAllByIds (Set<Integer> ids);
}
