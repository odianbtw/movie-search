package com.odian.moviesearch.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "movie_scores")
public class MovieScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float score;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
}
