package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.GenreDTOMapper;
import com.odian.moviesearch.api.model.GenreDTO;
import com.odian.moviesearch.core.services.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
    private final GenreDTOMapper mapper;

    @GetMapping
    public List<GenreDTO> findAll () {
        return mapper.to(genreService.findAll());
    }

}
