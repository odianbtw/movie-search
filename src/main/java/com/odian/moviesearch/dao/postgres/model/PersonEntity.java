package com.odian.moviesearch.dao.postgres.model;


import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "people")
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
    @OneToMany(mappedBy = "person")
    private List<MovieCreditEntity> movieCredits;
    @ManyToMany
    @JoinTable(name = "people_medias",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<MediaEntity> medias;

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
