package com.odian.moviesearch.api.model;

public record StatisticsDTO(
        Double rating,
        Integer amountOfReviews,
        Float popularity,
        Float trending
) {
}
