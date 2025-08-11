package com.odian.moviesearch.dao.postgres.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "film_popularity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmPopularityEntity {
    @Id
    @Column(name = "film_id")
    private UUID filmId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "film_id")
    private FilmEntity film;

    private Float rating;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
