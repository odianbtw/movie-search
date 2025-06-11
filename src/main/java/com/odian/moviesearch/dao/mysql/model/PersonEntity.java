package com.odian.moviesearch.dao.mysql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "persons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @ManyToMany
    @JoinTable(name = "person_media",
        joinColumns = @JoinColumn(name = "person_id"),
        inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<MediaEntity> medias;

    @OneToMany(mappedBy = "person")
    private List<MovieCreditEntity> credits;

    private String biography;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
}
