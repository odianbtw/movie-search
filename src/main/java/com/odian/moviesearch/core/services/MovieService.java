package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.model.Movie;
import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.utils.Pageable;

public interface MovieService {
    Movie create (Movie movie);
    Movie findById (Long id);
    PagedResponse<Movie> findAll (Pageable pageable);

}
