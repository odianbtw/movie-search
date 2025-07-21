package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.port.out.MovieRepository;
import com.odian.moviesearch.core.domain.model.Movie;
import com.odian.moviesearch.dao.postgres.mapper.MovieEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class DefaultMovieRepository implements MovieRepository {

    private final MovieEntityMapper movieMapper;

    @Override
    public Movie create(Movie movie) {

    }
}
