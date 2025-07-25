package com.odian.moviesearch.api.model;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EpisodeDTO (
        Long id,
        String imdbId,
        short seasonNumber,
        short episodeNumber,
        String title,
        String description,
        LocalDate releaseDate,
        Integer durationMinutes,
        String coverUrl
) implements Comparable<EpisodeDTO> {


    @Override
    public int compareTo(EpisodeDTO o) {
        return Integer.compare(episodeNumber, o.episodeNumber);
    }
}
