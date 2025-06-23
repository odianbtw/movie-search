package com.odian.moviesearch.unit.api.mapper;

import com.odian.moviesearch.api.mapper.GenreDTOMapper;
import com.odian.moviesearch.api.model.GenreDTO;
import com.odian.moviesearch.core.model.Genre;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenreDTOMapperTest {
    private final GenreDTOMapper subject = Mappers.getMapper(GenreDTOMapper.class);

    @Test
    void testToGenreDTO() {
        Genre genre = new Genre(1, "Action");
        GenreDTO genreDTO = new GenreDTO(1, "Action");
        assertEquals(genreDTO, subject.to(genre));
    }

    @Test
    void testToGenreListDTO() {
        var list = List.of(new Genre(1, "Action"));
        var dtoList = List.of(new GenreDTO(1, "Action"));
        assertEquals(dtoList, subject.to(list));
    }
}
