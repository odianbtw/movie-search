package com.odian.moviesearch.core.application.service;

import com.odian.moviesearch.core.application.exception.NotFoundException;
import com.odian.moviesearch.core.application.model.Pageable;
import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.application.port.out.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultSeriesService implements SeriesService {

    private final SeriesRepository seriesRepository;

    @Override
    public Title findById(Long id) {
        return seriesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Series with this id not found"));
    }

    @Transactional
    @Override
    public Title create(Title title) {
        return seriesRepository.create((Series) title);
    }

    @Transactional
    @Override
    public Episode addEpisode(Long seriesId, Episode episode) {
        return seriesRepository.addEpisode(seriesId, episode);
    }

    @Override
    public Episode findEpisodeById(Long id) {
        return seriesRepository.findEpisodeById(id)
                .orElseThrow(() -> new NotFoundException("Episode with this id not found"));
    }

    @Override
    public Set<Episode> findSeasonBySeriesId(Long id, Integer seasonNumber) {
        return seriesRepository.findSeasonBySeriesId(id, seasonNumber);
    }

    @Override
    public Set<Integer> findSeasonNumbersBySeriesId(Long id) {
        return seriesRepository.findSeasonNumbersBySeriesId(id);
    }

    @Override
    public PagedResponse<Series> findAll(Pageable pageable) {
        return seriesRepository.findAll(pageable);
    }


}
