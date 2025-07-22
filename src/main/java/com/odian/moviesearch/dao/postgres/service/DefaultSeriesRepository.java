package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.port.out.SeriesRepository;
import com.odian.moviesearch.core.domain.model.Series;
import com.odian.moviesearch.core.domain.model.TitleType;
import com.odian.moviesearch.dao.postgres.mapper.SeriesEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DefaultSeriesRepository implements SeriesRepository {

    private final SeriesEntityMapper seriesMapper;
    private final SpringDataSeriesRepository seriesRepository;

    @Override
    public Series create(Series series) {
        var entity = seriesRepository.save(seriesMapper.domainToEntity(series));
        return seriesMapper.entityToDomain(entity);
    }

    @Override
    public Optional<Series> findById(Long id) {
        var entity = seriesRepository.findById(id, TitleType.SERIES).orElse(null);
        return Optional.ofNullable(seriesMapper.entityToDomain(entity));
    }
}
