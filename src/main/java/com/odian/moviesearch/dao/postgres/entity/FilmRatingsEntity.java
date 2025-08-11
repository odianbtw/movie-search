package com.odian.moviesearch.dao.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "film_ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmRatingsEntity {

    @Id
    @Column(name = "film_id")
    private UUID filmId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "film_id")
    private FilmEntity film;

    @Column(nullable = false, precision = 2, scale = 2)
    private Double rating;

    @Column(name = "amount_of_ratings", nullable = false)
    private Integer amountOfRatings;
}
