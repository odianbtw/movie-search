package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.FilmDTOMapper;
import com.odian.moviesearch.api.model.FilmCreateRequest;
import com.odian.moviesearch.api.model.FilmDTO;
import com.odian.moviesearch.core.application.port.in.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;
    private final FilmDTOMapper filmDTOMapper;

    @GetMapping("/{id}")
    public FilmDTO findById (@PathVariable UUID id) {
        return filmDTOMapper
                .domainToDto(filmService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create (@RequestBody FilmCreateRequest filmCreateRequest) {
        filmDTOMapper.dtoCreateToDomain(filmCreateRequest);
    }
}
