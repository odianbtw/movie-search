package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.SeriesDTOMapper;
import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.application.port.in.SeriesService;
import com.odian.moviesearch.core.domain.model.Series;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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


    @PostMapping("/{id}/episodes")
    public ResponseEntity<Void> addEpisode (@PathVariable Long id, @RequestBody EpisodeRequest episodeRequest) {
        var episode = seriesService.addEpisode(id, seriesDTOMapper.dtoRequestToDomain(episodeRequest));
        URI location = URI.create("/series/" + id + "/episodes/" + episode.getId().id());
        return ResponseEntity
                .created(location)
                .build();
    }

    @GetMapping("/{id}/seasons")
    public Set<Integer> findSeasonNumbers (@PathVariable Long id) {
        return seriesService.findSeasonNumbersBySeriesId(id);
    }

    @GetMapping("/{id}/episodes")
    public Set<EpisodeDTO> findSeasonBySeriesId (@PathVariable Long id, @RequestParam("seasonNumber") Integer seasonNumber) {
        var episodes = seriesService.findSeasonBySeriesId(id, seasonNumber);
        return episodes
                .stream()
                .map(seriesDTOMapper::domainToDto)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @GetMapping("/episodes/{id}")
    public EpisodeDTO findEpisodeById (@PathVariable Long id) {
        var episode = seriesService.findEpisodeById(id);
        return seriesDTOMapper.domainToDto(episode);
    }
}
