package com.odian.moviesearch.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String slogan;

    private String description;

    private List<Genre> genres;

    private List<Country> filmedAt;

    private LocalDate releaseDate;

    private Float score;

    private Integer durationTime;

    private Instant createdAt;

    private Instant updatedAt;

}
