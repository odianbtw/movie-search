package com.odian.moviesearch.unit.api.mapper;

import com.odian.moviesearch.api.mapper.FilmDTOMapper;
import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.domain.model.*;
import com.odian.moviesearch.unit.util.FilmUtils;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmDTOMapperTest {


    private final static FilmDTOMapper subject = Mappers.getMapper(FilmDTOMapper.class);

    @Test
    public void testDomainToDtoMapping () {
        Film film = FilmUtils.getFilm();
        FilmDTO expected = FilmUtils.createDto(film);
        assertEquals(expected, subject.domainToDto(film));
    }



}
