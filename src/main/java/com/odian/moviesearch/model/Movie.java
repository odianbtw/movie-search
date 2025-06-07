package com.odian.moviesearch.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class Movie {

    private Long id;

    private String name;

    private String slogan;

    private String description;

    private List<Genre> genres;

    private LocalDate releaseDate;

    private Float score;

    private Integer durationTime;

    private Instant createdAt;

    private Instant updatedAt;

}
