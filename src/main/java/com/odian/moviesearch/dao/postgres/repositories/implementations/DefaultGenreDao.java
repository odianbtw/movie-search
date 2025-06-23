package com.odian.moviesearch.dao.postgres.repositories.implementations;

import com.odian.moviesearch.core.dao.GenreDao;
import com.odian.moviesearch.core.model.Genre;
import com.odian.moviesearch.dao.postgres.mapper.GenreEntityMapper;
import com.odian.moviesearch.dao.postgres.repositories.spring.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DefaultGenreDao implements GenreDao {
    private final GenreRepository genreRepository;
    private final GenreEntityMapper mapper;

    @Override
    public List<Genre> findAll() {
        return mapper.to(genreRepository.findAll());
    }

    @Override
    public Genre create(Genre genre) {
        var entity = mapper.to(genre);
        return mapper.to(genreRepository.save(entity));
    }
}
