package com.odian.moviesearch.dao.postgres.model;


import com.odian.moviesearch.dao.postgres.model.enums.MovieRatingEntity;
import com.odian.moviesearch.dao.postgres.model.enums.MovieTypeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
    private MovieRatingEntity movieRating;
    @Enumerated(EnumType.STRING)
    private MovieTypeEntity movieType;
    @ManyToMany
    @JoinTable(name = "movie_genre",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<GenreEntity> genres;
    @ManyToMany
    @JoinTable(name = "movie_country",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private List<CountryEntity> countries;
    @ManyToMany
    @JoinTable(name = "movie_company",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "company_id"))
    private List<CompanyEntity> companies;
    @OneToOne(mappedBy = "movie")
    private MovieScoreEntity score;
    @OneToMany(mappedBy = "movie")
    private List<MovieCreditEntity> movieCredits;
    @ManyToMany
    @JoinTable(name = "movie_media",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<MediaEntity> medias;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;


    @PrePersist
    private void setCreationTime () {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
    @PreUpdate
    private void setUpdateTime () {
        this.updatedAt = Instant.now();
    }
}
