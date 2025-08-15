package com.odian.moviesearch.unit.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Genre;
import com.odian.moviesearch.dao.postgres.entity.GenreEntity;
import com.odian.moviesearch.dao.postgres.mapper.GenreEntityMapper;
import com.odian.moviesearch.unit.util.GenreUtils;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenreEntityMapperTest {

    private static final GenreEntityMapper subject = Mappers.getMapper(GenreEntityMapper.class);

    @Test
    public void testEntityToDomain () {
        GenreEntity entity = GenreUtils.getGenreEntity();
        Genre expected = new Genre(entity.getId(), entity.getName());
        assertEquals(expected, subject.entityToDomain(entity));
    }
}
