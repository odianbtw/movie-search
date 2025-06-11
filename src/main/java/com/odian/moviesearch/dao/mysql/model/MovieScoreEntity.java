package com.odian.moviesearch.dao.mysql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "movie_scores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float score;
    @OneToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
}
