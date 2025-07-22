package com.odian.moviesearch.api.model;

import com.odian.moviesearch.core.domain.model.AgeRating;
import lombok.Builder;

import java.util.Set;

@Builder
public record SeriesDTO(
        Long id,
        String imdbId,
        String title,
        String slogan,
        String description,
        Float score,
        Set<CountryDTO> countries,
        Set<GenreDTO> genres,
        Set<ProductionStudioShort> productionStudios,
        AgeRating ageRating,
        String coverUrl,
        String trailerUrl,
        Integer budget,
        Integer revenue
) {
}
