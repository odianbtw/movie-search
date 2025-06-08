package com.odian.moviesearch.model;

import com.odian.moviesearch.model.enums.MovieRating;
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
    @ManyToMany
    @JoinTable(name = "movie_genre",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "country_movie",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "country_id"))
    private List<Country> filmedAt;

    @OneToMany(mappedBy = "movie")
    private List<MovieScore> movieScores;

    @OneToMany(mappedBy = "movie")
    private List<Award> awards;

    @ManyToMany
    @JoinTable(name = "production_studio_movie",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "production_studio_id"))
    private List<ProductionStudio> productionStudios;

    @ManyToMany
    @JoinTable(name = "movie_media",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<Media> medias;

    @OneToMany(mappedBy = "movie")
    private List<MovieCredit> credits;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column(name = "duration_time")
    private Integer durationTime;
    @Enumerated(EnumType.STRING)
    private MovieRating rating;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;

}
