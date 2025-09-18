package com.odian.moviesearch.dao.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    @EqualsAndHashCode.Exclude
    private FilmEntity film;

    @Column(nullable = false)
    private Double rating = 0.0;

    @Column(name = "amount_of_ratings", nullable = false)
    private Integer amountOfRatings = 0;

    public FilmRatingsEntity (FilmEntity film) {
        this.filmId = film.getId();
        this.film = film;
    }

    public void setRating(Double rating) {
        if (rating < 0.0 || rating > 10.0)
            throw new IllegalArgumentException("Rating of the movie cannot be less than 0 or greater than 10.");
        this.rating = rating;
    }

    public void setAmountOfRatings(Integer amountOfRatings) {
        if (amountOfRatings < 0)
            throw new IllegalArgumentException("Amount of ratings cannot be less than 0.");
        this.amountOfRatings = amountOfRatings;
    }
}
