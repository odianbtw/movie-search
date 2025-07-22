package com.odian.moviesearch.dao.postgres.entity;

import com.odian.moviesearch.core.domain.model.AgeRating;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "movies_info")
@PrimaryKeyJoinColumn(name = "movie_id")
@Data
@NoArgsConstructor
@SuperBuilder
public class MovieInfoEntity extends TitleEntity {

    private String slogan;
    private String description;
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "age_rating")
    private AgeRating ageRating;
    private Integer budget;
    private Integer revenue;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;


}
