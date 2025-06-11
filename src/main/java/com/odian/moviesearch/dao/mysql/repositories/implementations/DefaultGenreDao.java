package com.odian.moviesearch.dao.mysql.repositories.implementations;

import com.odian.moviesearch.core.GenreDao;
import com.odian.moviesearch.core.model.Genre;
import com.odian.moviesearch.dao.mysql.mapper.GenreMapper;
import com.odian.moviesearch.dao.mysql.repositories.interfaces.GenreRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class DefaultGenreDao implements GenreDao {

    private final GenreRepository genreRepository;
    private final GenreMapper mapper;

    @Override
    public List<Genre> findAll() {
        return mapper.to(genreRepository.findAll());
    }
}
