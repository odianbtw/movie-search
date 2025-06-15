package com.odian.moviesearch.dao.postgres.repository.implementations;


import com.odian.moviesearch.core.dao.GenreDao;
import com.odian.moviesearch.core.exceptions.DaoException;
import com.odian.moviesearch.core.model.Genre;
import com.odian.moviesearch.dao.postgres.mapper.GenreEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.interfaces.spring.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DefaultGenreDao implements GenreDao {
    private final GenreRepository repository;
    private final GenreEntityMapper mapper;

    @Override
    public List<Genre> findAll() {
        try {
            return mapper.to(repository.findAll());
        } catch (Exception e) {
            throw new DaoException("Unexpected error occurred during connection to the database");
        }
    }
}
