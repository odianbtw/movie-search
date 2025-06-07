package com.odian.moviesearch.model.enums;

import com.odian.moviesearch.model.Genre;

public enum GenreName {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SCIENCE_FICTION("Science Fiction"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western"),
    DOCUMENTARY("Documentary"),
    FAMILY("Family"),
    BIOGRAPHY("Biography"),
    HISTORY("History"),
    MUSICAL("Musical"),
    SPORT("Sport");

    private final String name;

    GenreName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Genre toEntity() {
        Genre genre = new Genre();
        genre.setName(this.name);
        return genre;
    }
}
