package com.odian.moviesearch.core;

import com.odian.moviesearch.core.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDao {
    List<Movie> findByName(String name);
    Optional<Movie> findById(Long id);
}
