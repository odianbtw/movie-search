package com.odian.moviesearch.core.dao;

import com.odian.moviesearch.core.model.Movie;

import java.util.Optional;

public interface MovieDao {
    Movie create(Movie movie);
    Optional<Movie> findById (Long id);
}
