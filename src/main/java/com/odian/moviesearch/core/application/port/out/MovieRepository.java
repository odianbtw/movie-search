package com.odian.moviesearch.core.application.port.out;

import com.odian.moviesearch.core.application.model.Pageable;
import com.odian.moviesearch.core.application.model.PagedResponse;

import java.util.Optional;

public interface MovieRepository {
    Movie create (Movie movie);
    Optional<Movie> findById (Long id);
    PagedResponse<Movie> findAll (Pageable pageable);
}
