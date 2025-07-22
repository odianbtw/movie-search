package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.SeriesDTOMapper;
import com.odian.moviesearch.api.model.SeriesDTO;
import com.odian.moviesearch.api.model.SeriesRequestDTO;
import com.odian.moviesearch.core.application.port.in.SeriesService;
import com.odian.moviesearch.core.domain.model.Series;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesService seriesService;
    private final SeriesDTOMapper seriesDTOMapper;

    @GetMapping("/{id}")
    public SeriesDTO findById (@PathVariable Long id) {
        return seriesDTOMapper.domainToDto((Series) seriesService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SeriesDTO> create (@RequestBody SeriesRequestDTO request) {
        var series = seriesService.create(seriesDTOMapper.dtoToDomain(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(seriesDTOMapper.domainToDto((Series) series));
    }

}
