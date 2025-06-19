package com.odian.moviesearch.core.model;

import com.odian.moviesearch.core.model.enums.MovieRating;
import com.odian.moviesearch.core.model.enums.MovieType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
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
    private List<Genre> genres;
    private List<Country> countries;
    private List<Company> companies;
    private Float score;
    private List<Media> medias;
    private LocalDate releaseDate;
    private Integer durationTime;
    private MovieType movieType;
    private MovieRating movieRating;
    private Integer budget;
    private Integer revenue;
}
