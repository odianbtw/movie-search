package com.odian.moviesearch.core.application.port.out;


import com.odian.moviesearch.core.domain.model.Genre;

import java.util.Set;

public interface GenreRepository {
    Set<Genre> findAll ();
    void create (Genre country);
}
