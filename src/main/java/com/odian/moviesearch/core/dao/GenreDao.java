package com.odian.moviesearch.core.dao;

import com.odian.moviesearch.core.model.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> findAll();
}
