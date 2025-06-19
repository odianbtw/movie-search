package com.odian.moviesearch.api.controller;

import com.odian.moviesearch.api.mapper.MovieDTOMapper;
import com.odian.moviesearch.api.model.MovieRequest;
import com.odian.moviesearch.core.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieDTOMapper mapper;
    private final MovieService service;

    @PutMapping
    public ResponseEntity<?> create (@RequestBody MovieRequest movieRequest) {
        service.create(mapper.to(movieRequest));
        return null;
    }

}
