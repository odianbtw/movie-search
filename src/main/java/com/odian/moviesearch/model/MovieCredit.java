package com.odian.moviesearch.model;

import jakarta.persistence.*;


@Entity
@Table(name = "movie_credits")
public class MovieCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

}
