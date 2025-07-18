package com.odian.moviesearch.core.application.service;

import com.odian.moviesearch.core.application.port.in.GenreService;
import com.odian.moviesearch.core.application.port.out.GenreRepository;
import com.odian.moviesearch.core.domain.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultGenreService implements GenreService {

    private final GenreRepository genreRepository;


    @Override
    public Set<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Transactional
    @Override
    public void create(String name) {
        Genre genre = new Genre(null, name);
        genreRepository.create(genre);
    }
}
