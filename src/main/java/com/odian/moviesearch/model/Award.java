package com.odian.moviesearch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "awards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "awarded_at")
    private LocalDate awardedAt;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
}
