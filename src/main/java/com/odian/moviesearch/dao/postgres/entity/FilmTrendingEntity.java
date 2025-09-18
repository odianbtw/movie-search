package com.odian.moviesearch.dao.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "film_trending")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmTrendingEntity {
    @Id
    @Column(name = "film_id")
    private UUID filmId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "film_id")
    @EqualsAndHashCode.Exclude
    private FilmEntity film;

    @Column(name = "popularity")
    private Float rating = 0.0f;

    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    public FilmTrendingEntity (FilmEntity film) {
        this.filmId = film.getId();
        this.film = film;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
