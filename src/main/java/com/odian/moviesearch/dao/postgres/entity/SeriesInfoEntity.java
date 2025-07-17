package com.odian.moviesearch.dao.postgres.entity;


import com.odian.moviesearch.core.domain.model.AgeRating;
import jakarta.persistence.*;


@Entity
@Table(name = "series_info")
@PrimaryKeyJoinColumn(name = "series_id")
public class SeriesInfoEntity extends TitleEntity {

    private String slogan;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "age_rating", nullable = false, columnDefinition = "age_rating_enum")
    private AgeRating ageRating;

    private Integer budget;
    private Integer revenue;
}
