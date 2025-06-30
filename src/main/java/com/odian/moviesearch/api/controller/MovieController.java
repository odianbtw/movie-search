package com.odian.moviesearch.api.controller;

import com.odian.moviesearch.api.mapper.MovieCreditDTOMapper;
import com.odian.moviesearch.api.mapper.MovieDTOMapper;
import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.api.utils.PageableBinder;
import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.services.MovieCreditService;
import com.odian.moviesearch.core.services.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
    private final MovieCreditService creditService;
    private final PageableBinder binder;
    private final MovieCreditDTOMapper creditDTOMapper;

    @PostMapping
    public ResponseEntity<MovieDTO> create (@RequestBody MovieRequest movieRequest) {
        var movie = service.create(mapper.to(movieRequest));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.to(movie));
    }

    @GetMapping("/{id}")
    public MovieDTO findById (@PathVariable Long id) {
        return mapper.to(service.findById(id));
    }

    @GetMapping
    public PagedResponseDTO<MovieItemDTO> findAll (HttpServletRequest request) {
        var pageable = binder.pageableFromRequest(request);
        return mapper.to(service.findAll(pageable));
    }
    @PostMapping("/{id}/people")
    public ResponseEntity<MovieCreditDTO> addPerson (@PathVariable Long id, @Valid @RequestBody MovieCreditRequest request) {
        var credit = creditService.create(id, creditDTOMapper.to(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creditDTOMapper.to(credit));
    }
}
