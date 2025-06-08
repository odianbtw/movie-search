package com.odian.moviesearch.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country birthPlace;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @ManyToMany
    @JoinTable(name = "person_media",
        joinColumns = @JoinColumn(name = "person_id"),
        inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<Media> medias;

    @OneToMany(mappedBy = "person")
    private List<MovieCredit> credits;

    private String biography;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
}
