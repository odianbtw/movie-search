package com.odian.moviesearch.core.application.port.in;

import com.odian.moviesearch.core.application.model.Pageable;
import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.domain.model.Episode;
import com.odian.moviesearch.core.domain.model.Series;

import java.util.Set;

public interface SeriesService extends TitleService {
    Episode addEpisode (Long seriesId, Episode episode);
    Episode findEpisodeById (Long id);
    Set<Episode> findSeasonBySeriesId (Long id, Integer seasonNumber);
    Set<Integer> findSeasonNumbersBySeriesId (Long id);
    PagedResponse<Series> findAll (Pageable pageable);
}
