package com.odian.moviesearch.dao.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "essential_film_media")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EssentialFilmMediaEntity {

    @Id
    @Column(name = "film_id")
    private UUID filmId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "film_id")
    private FilmEntity film;

    @ManyToOne
    @JoinColumn(name = "poster_id")
    private MediaEntity poster;

    @ManyToOne
    @JoinColumn(name = "backdrop_id")
    private MediaEntity backdrop;

    @ManyToOne
    @JoinColumn(name = "trailer_id")
    private MediaEntity trailer;
}
