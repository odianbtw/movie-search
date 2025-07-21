package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.model.MovieRequestDTO;
import com.odian.moviesearch.core.application.port.in.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
//    private

    @GetMapping
    public void create (@RequestBody MovieRequestDTO movieRequest) {

    }
}
