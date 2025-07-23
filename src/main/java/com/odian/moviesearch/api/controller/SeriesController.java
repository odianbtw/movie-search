package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.SeriesDTOMapper;
import com.odian.moviesearch.api.model.SeriesDTO;
import com.odian.moviesearch.api.model.SeriesRequestDTO;
import com.odian.moviesearch.core.application.port.in.SeriesService;
import com.odian.moviesearch.core.domain.model.Series;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    public ResponseEntity<Void> create (@RequestBody SeriesRequestDTO request) {
        var series = seriesService.create(seriesDTOMapper.dtoToDomain(request));
        URI location = URI.create("/series/" + series.getTitleId().id());
        return ResponseEntity
                .created(location)
                .build();
    }

}
