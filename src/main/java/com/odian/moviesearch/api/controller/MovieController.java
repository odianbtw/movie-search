package com.odian.moviesearch.api.controller;

import com.odian.moviesearch.api.mapper.MovieDTOMapper;
import com.odian.moviesearch.api.model.MovieDTO;
import com.odian.moviesearch.api.model.MovieRequest;
import com.odian.moviesearch.api.utils.PageableBinder;
import com.odian.moviesearch.core.services.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieDTOMapper mapper;
    private final MovieService service;
    private final PageableBinder binder;

    @PostMapping
    public ResponseEntity<MovieDTO> create (@RequestBody MovieRequest movieRequest) {
        var movie = service.create(mapper.to(movieRequest));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.to(movie));
    }

    @GetMapping("/{id}")
    public MovieDTO findById (Long id) {
        return mapper.to(service.findById(id));
    }

    @GetMapping
    public void findAll (HttpServletRequest request) {
        var pageable = binder.pageableFromRequest(request);
        service.findAll(pageable);
    }
}
