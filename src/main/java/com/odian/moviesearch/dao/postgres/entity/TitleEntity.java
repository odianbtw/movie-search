package com.odian.moviesearch.dao.postgres.entity;

import com.odian.moviesearch.core.domain.model.TitleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "titles")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class TitleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "titles_seq")
    @SequenceGenerator(name = "titles_seq",
            sequenceName = "titles_id_seq",
            allocationSize = 1)
    private Long id;

    @Column(name = "imdb_id")
    private String imdbId;

    private String title;

    @OneToOne(mappedBy = "title")
    private TitleScoreEntity score;

    @Enumerated(EnumType.STRING)
    @Column(name = "title_type", nullable = false, columnDefinition = "title_type_enum")
    private TitleType titleType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "title_genre",
        joinColumns = @JoinColumn(name = "title_id"),
        inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<GenreEntity> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "title_country",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<CountryEntity> countries;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "title_company",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Set<ProductionCompanyEntity> companies;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "title_media",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private Set<MediaEntity> medias;

    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = Instant.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
