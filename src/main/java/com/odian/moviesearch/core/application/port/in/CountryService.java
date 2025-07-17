package com.odian.moviesearch.core.application.port.in;



import com.odian.moviesearch.core.domain.model.Country;

import java.util.Set;

public interface CountryService {
    Set<Country> findAll();
    void create (String name);
}
