package com.odian.moviesearch.dao.postgres.repository.implementations;

import com.odian.moviesearch.core.dao.CountryDao;
import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.dao.postgres.mapper.CountryMapper;
import com.odian.moviesearch.dao.postgres.repository.interfaces.spring.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DefaultCountryDao implements CountryDao {

    private final CountryRepository repository;
    private final CountryMapper mapper;
    @Override
    public List<Country> findAll() {
        return mapper.to(repository.findAll());
    }

    @Override
    public Optional<Country> findById(Integer id) {
        var country = repository.findById(id).orElse(null);
        return Optional.ofNullable(mapper.to(country));
    }
}
