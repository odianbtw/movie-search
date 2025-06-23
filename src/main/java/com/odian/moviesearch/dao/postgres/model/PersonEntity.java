package com.odian.moviesearch.dao.postgres.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "people")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String biography;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<MovieCreditEntity> movieCredits;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private MediaEntity photo;

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
