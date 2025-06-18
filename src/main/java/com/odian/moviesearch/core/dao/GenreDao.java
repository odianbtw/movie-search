package com.odian.moviesearch.core.dao;

import com.odian.moviesearch.core.model.Genre;

import java.util.List;
import java.util.Set;

public interface GenreDao {
    List<Genre> findAll();
    Set<Genre> findAllByIds(Set<Integer> ids);
}
