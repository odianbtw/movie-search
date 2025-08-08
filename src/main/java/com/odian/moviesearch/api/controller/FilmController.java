package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.model.FilmDTO;
import com.odian.moviesearch.core.application.port.in.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @GetMapping("/{id}")
    public FilmDTO findById (@PathVariable UUID id) {
        return filmService.findById(id);
    }
}
