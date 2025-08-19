package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record FilmCreateRequest(
        String name,
        String originalName,
        @NotNull ExternalLinksDTO externalLinks,
        String tagline,
        String description,
        LocalDate releaseDate,
        Integer runtime,
        Set<Integer> genreIds,
        Set<Integer> countryIds,
        Set<Integer> languageIds,
        Set<UUID> studioIds,
        Set<UUID> directorIds,
        Set<KeywordDTO> keywords,
        String posterUrl,
        String backdropUrl,
        String trailerUrl
) {
}
