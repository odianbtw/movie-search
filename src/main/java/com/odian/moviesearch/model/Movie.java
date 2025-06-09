package com.odian.moviesearch.model;

import com.odian.moviesearch.model.enums.MovieRating;
import com.odian.moviesearch.model.enums.MovieType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String slogan;
    private String description;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_genre",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "country_movie",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "country_id"))
    private List<Country> filmedAt;

    @OneToOne(mappedBy = "movie")
    private MovieScore movieScore;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Award> awards;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "production_studio_movie",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "production_studio_id"))
    private List<ProductionStudio> productionStudios;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_media",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<Media> medias;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<MovieCredit> movieCredits;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column(name = "duration_time")
    private Integer durationTime;
    @Enumerated(EnumType.STRING)
    private MovieRating rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "movie_type")
    private MovieType movieType;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Review> reviews;

    private Long budget;

    private Long revenue;

    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;

}
