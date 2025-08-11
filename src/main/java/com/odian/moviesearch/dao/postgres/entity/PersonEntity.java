package com.odian.moviesearch.dao.postgres.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "people")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String slug;

    private String name;
    private String biography;

    @ManyToOne
    @JoinColumn(name = "profile_photo_id")
    private MediaEntity mediaEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "people_keyword",
        joinColumns = @JoinColumn(name = "person_id"),
        inverseJoinColumns = @JoinColumn(name = "keyword_id"))
    private Set<KeywordEntity> keywords;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;


    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = Instant.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
