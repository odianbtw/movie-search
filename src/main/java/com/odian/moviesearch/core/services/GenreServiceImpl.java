package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.GenreDao;
import com.odian.moviesearch.core.GenreService;
import com.odian.moviesearch.core.model.Genre;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }
}
