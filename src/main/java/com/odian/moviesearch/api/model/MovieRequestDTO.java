package com.odian.moviesearch.api.model;

import com.odian.moviesearch.core.domain.model.AgeRating;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public record MovieRequestDTO(
        String imdbId,
        @NotNull String title,
        String slogan,
        String description,
        Set<Integer> genreIds,
        Set<Integer> countryIds,
        Set<Long> productionCompanyIds,
        AgeRating ageRating,
        String coverUrl,
        String trailerUrl,
        Integer budget,
        Integer revenue,
        Integer durationMinutes,
        @NotNull LocalDate releaseDate
) {
}
