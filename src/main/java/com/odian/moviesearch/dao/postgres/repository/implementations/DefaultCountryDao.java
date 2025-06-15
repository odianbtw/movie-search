package com.odian.moviesearch.dao.postgres.repository.implementations;

import com.odian.moviesearch.core.dao.CountryDao;
import com.odian.moviesearch.core.exceptions.DaoException;
import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.dao.postgres.mapper.CountryEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.interfaces.spring.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DefaultCountryDao implements CountryDao {

    private final CountryRepository repository;
    private final CountryEntityMapper mapper;
    @Override
    public List<Country> findAll() {
        try {
            return mapper.to(repository.findAll());
        } catch (Exception e) {
            throw new DaoException("Unexpected error occurred during connection to the database");
        }
    }

    @Override
    public Optional<Country> findById(Integer id) {
        try {
            var country = repository.findById(id).orElse(null);
            return Optional.ofNullable(mapper.to(country));
        } catch (Exception e) {
            throw new DaoException("Unexpected error occurred during connection to the database");
        }
    }
}
