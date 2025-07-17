package com.odian.moviesearch.core.application.port.in;


import com.odian.moviesearch.core.domain.model.Genre;

import java.util.Set;

public interface GenreService {
    Set<Genre> findAll();
    void create (String name);
}
