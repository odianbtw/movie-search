package com.odian.moviesearch.core.application.service;


import com.odian.moviesearch.core.application.port.in.CountryService;
import com.odian.moviesearch.core.application.port.out.CountryRepository;
import com.odian.moviesearch.core.domain.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultCountryService implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Set<Country> findAll() {
        return countryRepository.findAll();
    }

    @Transactional
    @Override
    public void create(String name) {
        Country country = new Country(null, name);
        countryRepository.create(country);
    }
}
