package com.odian.moviesearch.api.model;

import com.odian.moviesearch.core.domain.model.AgeRating;


public record SeriesItemDTO(
        Long id,
        String name,
        String coverUrl,
        Float score,
        AgeRating ageRating
) {
}
