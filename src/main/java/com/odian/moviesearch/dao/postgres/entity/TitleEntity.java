package com.odian.moviesearch.dao.postgres.entity;

import com.odian.moviesearch.core.domain.model.TitleType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "titles")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class TitleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imdb_id")
    private String imdbId;

    private String title;

    @OneToOne(mappedBy = "title", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, optional = false)
    private TitleScoreEntity score;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "title_type", nullable = false, columnDefinition = "title_type_enum")
    private TitleType titleType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "title_genre",
        joinColumns = @JoinColumn(name = "title_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<GenreEntity> genres;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "title_country",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<CountryEntity> countries;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "title_company",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Set<ProductionCompanyEntity> companies;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "title_media",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private Set<MediaEntity> medias;

    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = Instant.now();
        score = new TitleScoreEntity();
        score.setTitle(this);
        score.setScore(0.0f);
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
