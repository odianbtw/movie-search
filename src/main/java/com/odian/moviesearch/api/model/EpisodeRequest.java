package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EpisodeRequest(
        String imdbId,
        @NotNull short seasonNumber,
        @NotNull short episodeNumber,
        @NotNull String title,
        String description,
        @NotNull LocalDate releaseDate,
        @NotNull Integer durationMinutes,
        String coverUrl
) {
}
