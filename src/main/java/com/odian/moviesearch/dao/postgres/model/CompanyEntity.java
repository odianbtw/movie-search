package com.odian.moviesearch.dao.postgres.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private CountryEntity country;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logo_id")
    private MediaEntity logo;
    @ManyToMany(mappedBy = "companies", fetch = FetchType.LAZY)
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
