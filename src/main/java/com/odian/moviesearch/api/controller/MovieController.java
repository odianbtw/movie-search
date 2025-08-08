package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.util.PageableBuilder;
import com.odian.moviesearch.api.util.validator.MoviePageableValidator;
import com.odian.moviesearch.core.application.model.PagedResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final MovieDTOMapper mapper;
    private final MoviePageableValidator moviePageableValidator;
    private final TitleCrewDTOMapper titleCrewDTOMapper;

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

    @GetMapping
    public PagedResponse<MovieItemDTO> findAll (HttpServletRequest request) {
        var pageable = PageableBuilder.fromHttpRequest(request);
        moviePageableValidator.validate(pageable);
        var res = movieService.findAll(pageable);
        return new PagedResponse<>(
                res.totalItems(),
                res.totalPages(),
                res.currentPage(),
                res.pageSize(),
                res.items().stream().map(mapper::domainToItemDto).toList()
        );
    }

    @PostMapping("/{id}/crew")
    public ResponseEntity<Void> addMovieCredit (@RequestBody TitleCreditRequest request, @PathVariable Long id) {
        if (!Objects.equals(id, request.titleId()))
            throw new IllegalArgumentException("Title id in the body different from title id in request path");
        titleCrewDTOMapper.dtoRequestToDomain(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

}
