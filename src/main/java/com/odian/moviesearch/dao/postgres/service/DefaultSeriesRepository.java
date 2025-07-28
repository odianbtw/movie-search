package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.model.Pageable;
import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.application.port.out.SeriesRepository;
import com.odian.moviesearch.core.domain.model.Episode;
import com.odian.moviesearch.core.domain.model.Series;
import com.odian.moviesearch.dao.postgres.entity.SeriesInfoEntity;
import com.odian.moviesearch.dao.postgres.mapper.PageableMapper;
import com.odian.moviesearch.dao.postgres.mapper.SeriesEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataEpisodeRepository;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataSeriesRepository;
import com.odian.moviesearch.dao.postgres.service.util.SeriesSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Repository
@RequiredArgsConstructor
public class DefaultSeriesRepository implements SeriesRepository {

    private final SeriesEntityMapper seriesMapper;
    private final SpringDataSeriesRepository seriesRepository;
    private final SpringDataEpisodeRepository episodeRepository;
    private final PageableMapper pageableMapper;
    private final SeriesSpecification seriesSpecification;

    @Override
    public Series create(Series series) {
        var entity = seriesRepository.saveAndFlush(seriesMapper.domainToEntity(series));
        return seriesMapper.entityToDomain(entity);
    }

    @Override
    public Optional<Series> findById(Long id) {
        var entity = seriesRepository.findByIdWithDetails(id).orElse(null);
        return Optional.ofNullable(seriesMapper.entityToDomain(entity));
    }

    @Override
    public Episode addEpisode(Long seriesId, Episode episode) {
        var series = new SeriesInfoEntity();
        series.setId(seriesId);
        var entity = seriesMapper.domainToEntity(episode);
        entity.setSeries(series);
        return seriesMapper.entityToDomain(episodeRepository.save(entity));
    }

    @Override
    public Optional<Episode> findEpisodeById(Long id) {
        var episode = episodeRepository.findById(id).orElse(null);
        return Optional.ofNullable(seriesMapper.entityToDomain(episode));
    }

    @Override
    public Set<Episode> findSeasonBySeriesId(Long id, Integer seasonNumber) {
        var episodes = episodeRepository.findSeasonBySeriesId(id, seasonNumber);
        return seriesMapper.entityToDomain(episodes);
    }

    @Override
    public Set<Integer> findSeasonNumbersBySeriesId(Long id) {
        return new TreeSet<>(episodeRepository.countDistinctSeasonNumberBySeriesId(id));
    }

    @Override
    public PagedResponse<Series> findAll(Pageable pageable) {
        var springPageable = pageableMapper.springPageableFromCustom(pageable);
        var specification = seriesSpecification.fromPageable(pageable);
        var page = seriesRepository.findAll(specification, springPageable);
        var res = seriesRepository.findAllBySeries(page.getContent());
        return new PagedResponse<>(
                page.getTotalElements(),
                page.getTotalPages(),
                pageable.getCurrentPage(),
                (int) pageable.getPageSize(),
                res.stream().map(seriesMapper::entityToDomainItem).toList()
        );
    }
}
