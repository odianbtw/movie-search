package com.odian.moviesearch.dao.postgres.entity;

import com.odian.moviesearch.core.domain.model.AgeRating;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "movies_info")
@PrimaryKeyJoinColumn(name = "movie_id")
public class MovieInfoEntity extends TitleEntity {

    private String slogan;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "age_rating", nullable = false, columnDefinition = "age_rating_enum")
    private AgeRating ageRating;
    private Integer budget;
    private Integer revenue;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;
}
