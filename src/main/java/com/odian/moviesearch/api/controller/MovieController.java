package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.MovieDTOMapper;
import com.odian.moviesearch.api.model.MovieDTO;
import com.odian.moviesearch.api.model.MovieRequestDTO;
import com.odian.moviesearch.core.application.port.in.MovieService;
import com.odian.moviesearch.core.domain.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MovieDTO> create (@RequestBody MovieRequestDTO movieRequest) {
        var movie = movieService.create(mapper.dtoToDomain(movieRequest));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.domainToDto((Movie) movie));
    }
}
