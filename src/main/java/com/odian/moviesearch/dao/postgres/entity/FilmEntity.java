package com.odian.moviesearch.dao.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "films")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String slug;
    private String name;
    private String originalName;
    @Column(name = "imdb_id")
    private String imdbId;
    @Column(name = "tmdb_id")
    private String tmdbId;
    private String tagline;
    private String description;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    private Integer runtime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_media",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id")
    )
    private Set<MediaEntity> medias = new HashSet<>();

    @OneToOne(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private EssentialFilmMediaEntity essentialMedia;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenreEntity> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_country",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private Set<CountryEntity> countries;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_studio",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id")
    )
    private Set<ProductionStudioEntity> studios;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_language",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<LanguageEntity> languages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_keyword",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    private Set<KeywordEntity> keywords;

    @OneToOne(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private FilmRatingsEntity ratings;

    @OneToOne(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private FilmPopularityEntity popularity;

    @OneToOne(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private FilmTrendingEntity trending;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<FilmContributionEntity> filmContributions;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = this.updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
