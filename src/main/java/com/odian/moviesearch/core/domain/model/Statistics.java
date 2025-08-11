package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statistics {
    private Float rating;
    private Integer amountOfReviews;
    private Float popularity;
    private Float trending;
}
