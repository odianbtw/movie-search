package com.odian.moviesearch.core.dao;

import com.odian.moviesearch.core.model.Movie;
import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.utils.Pageable;

import java.util.Optional;

public interface MovieDao {
    Movie create(Movie movie);
    Optional<Movie> findById (Long id);
    PagedResponse<Movie> findAll (Pageable pageable);
}
