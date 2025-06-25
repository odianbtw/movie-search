package com.odian.moviesearch.unit.dao.postgres.mapper;

import com.odian.moviesearch.core.model.Genre;
import com.odian.moviesearch.dao.postgres.mapper.GenreEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.GenreEntityMapperImpl;
import com.odian.moviesearch.dao.postgres.model.GenreEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenreEntityMapperTest {

    private final GenreEntityMapper subject = new GenreEntityMapperImpl();

    @Test
    void testEntityToGenre () {
        GenreEntity entity = new GenreEntity();
        entity.setId(1);
        entity.setName("Thriller");
        Genre genre = new Genre(1, "Thriller");
        assertEquals(genre, subject.to(entity));
    }

    @Test
    void testEntityListToGenreList () {
        GenreEntity entity = new GenreEntity();
        entity.setId(1);
        entity.setName("Thriller");
        var entityList = List.of(entity);
        var genreList = List.of(new Genre(1, "Thriller"));
        assertEquals(genreList, subject.to(entityList));
    }

    @Test
    void testGenreToEntity () {
        Genre genre = new Genre(null, "Thriller");
        GenreEntity entity = new GenreEntity();
        entity.setName("Thriller");
        assertEquals(entity, subject.to(genre));
    }
}
