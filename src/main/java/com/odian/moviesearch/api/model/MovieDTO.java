package com.odian.moviesearch.api.model;

import com.odian.moviesearch.core.domain.model.AgeRating;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record MovieDTO (
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
        Integer revenue,
        Integer durationMinutes,
        LocalDate releaseDate
) {
}
