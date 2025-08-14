package com.odian.moviesearch.unit.util;

import com.odian.moviesearch.core.domain.model.Genre;
import com.odian.moviesearch.dao.postgres.entity.GenreEntity;

import java.time.Instant;

public class GenreUtils {
    public static GenreEntity getGenreEntity () {
        return new GenreEntity(
                2,
                "Thriller",
                Instant.now(),
                Instant.now()
        );
    }

    public static Genre getGenre () {
        return new Genre(
                1,
                "Thriller"
        );
    }
}
