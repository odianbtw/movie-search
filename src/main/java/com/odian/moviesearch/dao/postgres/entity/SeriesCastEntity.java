package com.odian.moviesearch.dao.postgres.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.io.Serializable;

@Entity
@Table(name = "series_cast")
@IdClass(SeriesCastEntity.Key.class)
public class SeriesCastEntity {

    @Id
    @Column(name = "episode_id")
    private Long episodeId;
    @Id
    @Column(name = "person_id")
    private Long personId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("episodeId")
    @JoinColumn(name = "episode_id")
    private SeriesContentEntity episode;

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
        private Long episodeId;
        private Long personId;
    }
}
