package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
