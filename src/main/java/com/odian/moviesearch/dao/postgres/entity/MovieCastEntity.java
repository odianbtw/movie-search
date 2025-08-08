package com.odian.moviesearch.dao.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.io.Serializable;

@Entity
@Table(name = "movie_cast")
@IdClass(MovieCastEntity.Key.class)
public class MovieCastEntity {
    @Id
    @Column(name = "movie_id")
    private Long movieId;
    @Id
    @Column(name = "person_id")
    private Long personId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private TitleEntity movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(nullable = false, columnDefinition = "title_role_enum")
    private TitleRole role;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Key implements Serializable {
        private Long movieId;
        private Long personId;
    }

}
