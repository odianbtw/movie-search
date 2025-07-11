package com.odian.moviesearch.dao.postgres.model;


import com.odian.moviesearch.core.model.enums.MovieRating;
import com.odian.moviesearch.core.model.enums.MovieType;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String slogan;
    private String description;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column(name = "duration_time")
    private Integer durationTime;
    private Integer budget;
    private Integer revenue;
    @Enumerated(EnumType.STRING)
    private MovieRating movieRating;
    @Enumerated(EnumType.STRING)
    private MovieType movieType;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_genre",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<GenreEntity> genres;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_country",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private List<CountryEntity> countries;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_company",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "company_id"))
    private List<CompanyEntity> companies;
    @OneToOne(mappedBy = "movie", cascade = CascadeType.PERSIST)
    private MovieScoreEntity score;
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<MovieCreditEntity> movieCredits;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "movie_media",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<MediaEntity> medias;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;


    @PrePersist
    private void prePersist () {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        MovieScoreEntity movieScoreEntity = new MovieScoreEntity();
        movieScoreEntity.setScore(0.0f);
        movieScoreEntity.setMovie(this);
        this.score = movieScoreEntity;
    }
    @PreUpdate
    private void preUpdate () {
        this.updatedAt = Instant.now();
    }
}
