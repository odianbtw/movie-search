package com.odian.moviesearch.core.model;

import com.odian.moviesearch.core.model.enums.MovieRating;
import com.odian.moviesearch.core.model.enums.MovieType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private Long id;
    private String name;
    private String slogan;
    private String description;
    private LocalDate releaseDate;
    private Integer durationTime;
    private Long budget;
    private Long revenue;
    private MovieRating rating;
    private MovieType type;
    private Set<Genre> genres;
    private Set<Country> filmedAt;
    private Float score;
    private Set<ProductionStudio> productionStudios;
}
