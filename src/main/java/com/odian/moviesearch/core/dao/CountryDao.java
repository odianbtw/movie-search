package com.odian.moviesearch.core.dao;

import com.odian.moviesearch.core.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDao {
    List<Country> findAll ();

    Optional<Country> findById(Integer id);
}
