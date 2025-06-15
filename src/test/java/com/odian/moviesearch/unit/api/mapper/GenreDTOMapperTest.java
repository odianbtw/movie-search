package com.odian.moviesearch.unit.api.mapper;

import com.odian.moviesearch.api.model.GenreDTO;
import com.odian.moviesearch.core.model.Genre;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GenreDTOMapperTest {
    private final GenreDTOMapper subject = new GenreDTOMapperImpl();

    @Test
    public void genreToGenreDTO () {
        Genre genre = new Genre(10, "Thriller");
        GenreDTO dto = new GenreDTO(10, "Thriller");
        assertEquals(dto, subject.to(genre));
    }

    @Test
    public void genreListToDTOs () {
        List<Genre> genres = List.of(new Genre(10, "Thriller"));
        List<GenreDTO> dtos = List.of(new GenreDTO(10, "Thriller"));
        assertEquals(dtos, subject.to(genres));
    }
}
