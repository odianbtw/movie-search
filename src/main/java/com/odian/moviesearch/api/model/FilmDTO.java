package com.odian.moviesearch.api.model;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
public record FilmDTO(
        UUID id,
        String slug,
        String name,
        String originalName,
        ExternalLinksDTO externalUrls,
        String tagline,
        String description,
        LocalDate releaseDate,
        Integer runtime,
        Float rating,
        Integer amountOfReviews,
        Float popularity,
        Float trending,
        String posterUrl,
        String backdropUrl,
        String trailerUrl,
        Set<NamedPersonItemDTO> directors,
        Set<GenreDTO> genres,
        Set<CountryDTO> countries,
        Set<StudioDTO> studios,
        Set<LanguageDTO> languages,
        Set<KeywordDTO> keywords
) {
}
