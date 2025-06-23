package com.odian.moviesearch.dao.postgres.model;

import com.odian.moviesearch.core.model.enums.MovieRole;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "movie_credits")
public class MovieCreditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PersonEntity person;
    @Enumerated(EnumType.STRING)
    private MovieRole role;

    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
    @PrePersist
    private void setCreationTime () {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
    @PreUpdate
    private void setUpdateTime () {
        this.updatedAt = Instant.now();
    }

}
