package com.odian.moviesearch.dao.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "series_content")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeriesContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imdb_id")
    private String imdbId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "series_id")
    private TitleEntity series;

    @OneToOne(mappedBy = "episode", fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    private EpisodeScoreEntity score;

    @Column(name = "season_number")
    private short seasonNumber;

    @Column(name = "episode_number")
    private short episodeNumber;

    @Column(name = "episode_name")
    private String episodeName;

    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "series_content_media",
            joinColumns = @JoinColumn(name = "series_content_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private Set<MediaEntity> medias;

    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = Instant.now();
        score = new EpisodeScoreEntity();
        score.setScore(0.0f);
        score.setEpisode(this);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }


}
