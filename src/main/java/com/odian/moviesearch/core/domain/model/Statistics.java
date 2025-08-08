package com.odian.moviesearch.core.domain.model;

import lombok.Data;

@Data
public class Statistics {
    private Float rating;
    private Integer amountOfReviews;
    private Float popularity;
    private Float trending;
}
