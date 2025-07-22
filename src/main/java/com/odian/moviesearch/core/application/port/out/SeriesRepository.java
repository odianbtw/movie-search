package com.odian.moviesearch.core.application.port.out;

import com.odian.moviesearch.core.domain.model.Series;

import java.util.Optional;

public interface SeriesRepository {
    Series create (Series series);
    Optional<Series> findById (Long id);
}
