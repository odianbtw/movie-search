package com.odian.moviesearch.core.application.port.in;

import com.odian.moviesearch.core.application.model.Pageable;
import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.domain.model.Movie;

public interface MovieService extends TitleService {
    PagedResponse<Movie> findAll (Pageable pageable);
}
