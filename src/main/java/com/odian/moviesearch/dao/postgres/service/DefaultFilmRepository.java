package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.port.out.FilmRepository;
import com.odian.moviesearch.core.domain.model.Film;
import com.odian.moviesearch.dao.postgres.mapper.FilmEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataFilmRepository;
import com.odian.moviesearch.dao.postgres.utils.specification.FilmSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
@RequiredArgsConstructor
public class DefaultFilmRepository implements FilmRepository {

    private final SpringDataFilmRepository filmRepository;
    private final FilmEntityMapper filmEntityMapper;
    private final FilmSpecification filmSpecification;

    @Override
    public Optional<Film> findById(UUID id) {
        var film = filmRepository.findOne(filmSpecification.findById(id))
                .orElse(null);
        return Optional.ofNullable(filmEntityMapper.entityToDomain(film));
    }
}
