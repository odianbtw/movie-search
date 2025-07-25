package com.odian.moviesearch.api.model;

import com.odian.moviesearch.core.domain.model.AgeRating;

import java.time.LocalDate;

public record MovieItemDTO(
        Long id,
        String name,
        String coverUrl,
        LocalDate releaseDate,
        Float score,
        Integer durationTime,
        AgeRating ageRating
) {
}
