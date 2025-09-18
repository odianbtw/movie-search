package com.odian.moviesearch.unit.util;

import com.odian.moviesearch.api.model.GenreDTO;
import com.odian.moviesearch.core.domain.model.Genre;
import com.odian.moviesearch.dao.postgres.entity.GenreEntity;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static GenreDTO getGenreDto (Genre genre) {
        return new GenreDTO(genre.getId(), genre.getName());
    }

    public static Set<GenreDTO> getGenresDto (Set<Genre> genres) {
        return genres.stream().map(GenreUtils::getGenreDto).collect(Collectors.toSet());
    }
}
