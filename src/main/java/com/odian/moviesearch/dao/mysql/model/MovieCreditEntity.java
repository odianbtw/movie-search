package com.odian.moviesearch.dao.mysql.model;

import com.odian.moviesearch.dao.mysql.model.enums.MovieRole;
import com.odian.moviesearch.dao.mysql.model.enums.MovieRoleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Entity
@Table(name = "movie_credits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieCreditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @Enumerated(EnumType.STRING)
    private MovieRoleEntity role;

    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
}
