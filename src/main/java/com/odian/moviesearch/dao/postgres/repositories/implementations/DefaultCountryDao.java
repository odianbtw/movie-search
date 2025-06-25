package com.odian.moviesearch.dao.postgres.repositories.implementations;

import com.odian.moviesearch.core.dao.CountryDao;
import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.dao.postgres.mapper.CountryEntityMapper;
import com.odian.moviesearch.dao.postgres.repositories.spring.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class DefaultCountryDao implements CountryDao {

    private final CountryRepository countryRepository;
    private final CountryEntityMapper mapper;

    @Override
    public List<Country> findAll() {
        return mapper.to(countryRepository.findAll());
    }

    @Override
    public Optional<Country> findById(Integer id) {
        return Optional.empty();
    }
}
