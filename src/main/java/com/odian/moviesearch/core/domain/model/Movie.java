package com.odian.moviesearch.core.domain.model;


import java.time.LocalDate;

public class Movie extends Title {

    private Integer durationMinutes;
    private LocalDate releaseDate;

    public Movie(Id titleId, String title, TitleInfo titleInfo, Float score) {
        super(titleId, title, titleInfo, score);
    }
}
