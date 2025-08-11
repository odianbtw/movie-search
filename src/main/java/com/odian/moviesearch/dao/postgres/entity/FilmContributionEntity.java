package com.odian.moviesearch.dao.postgres.entity;


import com.odian.moviesearch.core.domain.model.ContributorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "film_contributions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmContributionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private FilmEntity film;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @Column(name = "contributor_type")
    private ContributorType contributorType;

    @Column(name = "billing_order")
    private Integer billingOrder;

    @Column(name = "character_name")
    private String characterName;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = this.updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
