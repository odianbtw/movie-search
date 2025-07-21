package com.odian.moviesearch.core.domain.model;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Movie extends Title {

    private Integer durationMinutes;
    private LocalDate releaseDate;

    public Movie(Id titleId, String title, TitleInfo titleInfo, Float score, Integer durationMinutes, LocalDate releaseDate) {
        super(titleId, title, titleInfo, score);
        this.durationMinutes = durationMinutes;
        this.releaseDate = releaseDate;
    }
}
