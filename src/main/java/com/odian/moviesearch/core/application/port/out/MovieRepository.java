package com.odian.moviesearch.core.application.port.out;

import com.odian.moviesearch.core.domain.model.Movie;

public interface MovieRepository {
    Movie create (Movie movie);
}
