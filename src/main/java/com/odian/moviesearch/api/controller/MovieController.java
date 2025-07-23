package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.MovieDTOMapper;
import com.odian.moviesearch.api.model.MovieDTO;
import com.odian.moviesearch.api.model.MovieRequestDTO;
import com.odian.moviesearch.core.application.port.in.MovieService;
import com.odian.moviesearch.core.domain.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final MovieDTOMapper mapper;


    @GetMapping("/{id}")
    public MovieDTO findById (@PathVariable Long id) {
        return mapper.domainToDto((Movie) movieService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create (@RequestBody MovieRequestDTO movieRequest) {
        var movie = movieService.create(mapper.dtoToDomain(movieRequest));
        URI location = URI.create("/movies/" + movie.getTitleId().id());
        return ResponseEntity
                .created(location)
                .build();
    }
}
