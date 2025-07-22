package com.odian.moviesearch.dao.postgres.entity;


import com.odian.moviesearch.core.domain.model.AgeRating;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;


@Entity
@Table(name = "series_info")
@PrimaryKeyJoinColumn(name = "series_id")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class SeriesInfoEntity extends TitleEntity {

    private String slogan;
    private String description;
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "age_rating", nullable = false, columnDefinition = "age_rating_enum")
    private AgeRating ageRating;

    private Integer budget;
    private Integer revenue;
}
