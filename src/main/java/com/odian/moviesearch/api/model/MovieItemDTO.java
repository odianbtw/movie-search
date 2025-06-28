package com.odian.moviesearch.api.model;

import com.odian.moviesearch.core.model.enums.MovieRating;

import java.time.LocalDate;

public record MovieItemDTO(
        Long id,
        String name,
        String coverUrl,
        LocalDate releaseDate,
        Float score,
        Integer durationTime,
        MovieRating movieRating
) {
}
