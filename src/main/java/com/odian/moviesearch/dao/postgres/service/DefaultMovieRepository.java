package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.port.out.MovieRepository;
import com.odian.moviesearch.core.domain.model.Movie;
import com.odian.moviesearch.core.domain.model.TitleType;
import com.odian.moviesearch.dao.postgres.mapper.MovieEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class DefaultMovieRepository implements MovieRepository {

    private final MovieEntityMapper movieMapper;
    private final SpringDataMovieRepository movieRepository;


    @Override
    public Movie create(Movie movie) {
        var entity = movieMapper.domainToEntity(movie);
        return movieMapper.entityToDomain(movieRepository.save(entity));
    }

    @Override
    public Optional<Movie> findById(Long id) {
        var entity = movieRepository.findById(id, TitleType.MOVIE).orElse(null);
        return Optional.ofNullable(movieMapper.entityToDomain(entity));
    }
}
