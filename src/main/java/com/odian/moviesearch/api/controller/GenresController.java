package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.GenreMapper;
import com.odian.moviesearch.api.model.GenreDTO;
import com.odian.moviesearch.core.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenresController {

    private final GenreService genreService;
    private final GenreMapper mapper;

    @GetMapping
    public List<GenreDTO> findAll () {
        return mapper.to(genreService.findAll());
    }
}
