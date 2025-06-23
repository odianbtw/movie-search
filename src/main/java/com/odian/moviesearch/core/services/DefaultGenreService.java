package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.dao.GenreDao;
import com.odian.moviesearch.core.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGenreService implements GenreService {

    private final GenreDao genreDao;

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }

    @Override
    public Genre create(Genre genre) {
        return genreDao.create(genre);
    }
}
