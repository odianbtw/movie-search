package com.odian.moviesearch.services;


import com.odian.moviesearch.dto.GenreDTO;
import com.odian.moviesearch.model.Genre;
import com.odian.moviesearch.repositories.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<GenreDTO> findAll () {
        return genreRepository.findAll().stream()
                .map(GenreService::wrapper)
                .toList();
    }

    protected static GenreDTO wrapper (Genre genre) {
        return new GenreDTO(
                genre.getId(),
                genre.getName()
        );
    }

//    protected static Genre
}
