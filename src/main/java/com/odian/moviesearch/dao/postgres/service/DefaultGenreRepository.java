package com.odian.moviesearch.dao.postgres.service;


import com.odian.moviesearch.core.application.port.out.GenreRepository;
import com.odian.moviesearch.core.domain.model.Genre;
import com.odian.moviesearch.dao.postgres.mapper.GenreEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataGenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DefaultGenreRepository implements GenreRepository {

    private final SpringDataGenreRepository genreRepository;
    private final GenreEntityMapper genreMapper;

    @Override
    public Set<Genre> findAll() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::entityToDomain)
                .sorted()
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public void create(Genre genre) {
        genreRepository.save(genreMapper.domainToEntity(genre));
    }
}
