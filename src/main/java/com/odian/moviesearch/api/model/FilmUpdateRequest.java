package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record FilmUpdateRequest(
        @NotNull UUID id,
        @NotNull String name,
        @NotNull String originalName,
        @NotNull ExternalLinksDTO externalLinks,
        String tagline,
        String description,
        LocalDate releaseDate,
        Integer runtime,
        @NotNull Set<Integer> genreIds,
        @NotNull Set<Integer> countryIds,
        @NotNull Set<Integer> languageIds,
        @NotNull Set<UUID> studioIds,
        @NotNull Set<UUID> directorIds,
        @NotNull Set<KeywordDTO> keywords,
        String posterUrl,
        String backdropUrl,
        String trailerUrl
) {
}
