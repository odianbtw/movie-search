package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.dao.CountryDao;
import com.odian.moviesearch.core.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCountryService implements CountryService {
    private final CountryDao countryDao;
    @Override
    public List<Country> findAll() {
       return countryDao.findAll();
    }
}
