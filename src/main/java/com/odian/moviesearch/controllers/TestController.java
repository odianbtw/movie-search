package com.odian.moviesearch.controllers;


import com.odian.moviesearch.model.Genre;
import com.odian.moviesearch.repositories.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TestController {

    private final GenreRepository genreRepository;

    @GetMapping("/genres")
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }
}
