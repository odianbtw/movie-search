package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.port.out.CountryRepository;
import com.odian.moviesearch.core.domain.model.Country;
import com.odian.moviesearch.dao.postgres.mapper.CountryEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataCountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DefaultCountryRepository implements CountryRepository {

    private final SpringDataCountryRepository countryRepository;
    private final CountryEntityMapper countryMapper;

    @Override
    public Set<Country> findAll() {
        return countryRepository.findAll()
                .stream()
                .map(countryMapper::entityToDomain)
                .sorted()
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public void create(Country country) {
        countryRepository.save(countryMapper.domainToEntity(country));
    }
}
