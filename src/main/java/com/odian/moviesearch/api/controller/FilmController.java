package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.FilmDTOMapper;
import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.api.util.FilmCriteria;
import com.odian.moviesearch.core.application.model.RequestCriteria;
import com.odian.moviesearch.core.application.port.in.FilmService;
import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataFilmRepository;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataGenreRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @GetMapping
    public PagedResponseDTO<FilmItemDTO> findAll (@FilmCriteria RequestCriteria criteria) {
        var res = filmService.findAll(criteria);
        return new PagedResponseDTO<>(
                res.totalItems(),
                res.totalPages(),
                res.pageSize(),
                res.currentPage(),
                res.items().stream()
                        .map(filmDTOMapper::domainToShortDto)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }

    @PostMapping
    public ResponseEntity<Void> create (@Valid @RequestBody FilmCreateRequest filmCreateRequest) {
        var film = filmService.save(filmDTOMapper.dtoToDomain(filmCreateRequest));
        return ResponseEntity
                .created(URI.create("/films/"+film.getId()))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> edit (@PathVariable UUID id,
                                   @RequestBody FilmUpdateRequest filmUpdateRequest) {
        if (!Objects.equals(id, filmUpdateRequest.id()))
            throw new IllegalArgumentException("Id in url path and id in body must be equal");
        filmService.update(filmDTOMapper.dtoToDomain(filmUpdateRequest));
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

    @DeleteMapping("/id")
    public void delete (@PathVariable UUID id) {
        filmService.delete(id);
    }

}
