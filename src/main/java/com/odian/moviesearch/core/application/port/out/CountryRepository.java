package com.odian.moviesearch.core.application.port.out;


import com.odian.moviesearch.core.domain.model.Country;

import java.util.Set;

public interface CountryRepository {
    Set<Country> findAll ();
    void create (Country country);
}
