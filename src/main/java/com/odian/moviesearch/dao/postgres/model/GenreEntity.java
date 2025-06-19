package com.odian.moviesearch.dao.postgres.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "genres")
    private List<MovieEntity> movies;
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
