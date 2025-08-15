package com.odian.moviesearch.unit.dao.postgres.repository.service;

import com.odian.moviesearch.core.domain.model.Film;
import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import com.odian.moviesearch.dao.postgres.mapper.FilmEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataFilmRepository;
import com.odian.moviesearch.dao.postgres.service.DefaultFilmRepository;
import com.odian.moviesearch.unit.util.FilmUtils;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultFilmRepositoryTest {

    private static final SpringDataFilmRepository filmRepository = mock(SpringDataFilmRepository.class);
    private static final FilmEntityMapper filmMapper = mock(FilmEntityMapper.class);
    private static final DefaultFilmRepository subject = new DefaultFilmRepository(filmRepository, filmMapper);

    @Test
    public void testFindById () {
        Film expected = FilmUtils.getFilm();
        when(filmRepository.findByIdWithAllData(any(UUID.class))).thenReturn(Optional.of(FilmUtils.getFilmEntity()));
        when(filmMapper.entityToDomain(any(FilmEntity.class))).thenReturn(expected);
        var result = subject.findById(UUID.randomUUID());
        assertTrue(result.isPresent());
    }

}
