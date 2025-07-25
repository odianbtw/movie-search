package com.odian.moviesearch.core.application.port.out;

import com.odian.moviesearch.core.domain.model.Episode;
import com.odian.moviesearch.core.domain.model.Season;
import com.odian.moviesearch.core.domain.model.Series;

import java.util.Optional;
import java.util.Set;

public interface SeriesRepository {
    Series create (Series series);
    Optional<Series> findById (Long id);
    Episode addEpisode (Long seriesId, Episode episode);
    Optional<Episode> findEpisodeById (Long id);
    Set<Episode> findSeasonBySeriesId (Long id, Integer seasonNumber);
    Set<Integer> findSeasonNumbersBySeriesId (Long id);
}
