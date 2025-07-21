package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.GenreDTOMapper;
import com.odian.moviesearch.api.model.GenreDTO;
import com.odian.moviesearch.core.application.port.in.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;
    private final GenreDTOMapper genreMapper;


    @GetMapping
    public Set<GenreDTO> findAll () {
        return genreMapper.domainToDto(genreService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> create (@RequestBody GenreDTO genre) {
        genreService.create(genre.name());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

}
