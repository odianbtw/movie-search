package com.odian.moviesearch.api.model;

import com.odian.moviesearch.core.model.enums.MovieRating;
import com.odian.moviesearch.core.model.enums.MovieType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(
        @NotNull String name,
        String slogan,
        String description,
        @NotNull List<Integer> genreIds,
        @NotNull List<Integer> countryIds,
        List<Long> companyIds,
        String coverUrl,
        String trailerUrl,
        @NotNull LocalDate releaseDate,
        @NotNull Integer durationTime,
        @NotNull MovieType movieType,
        MovieRating movieRating,
        Integer budget,
        Integer revenue
) {
}
