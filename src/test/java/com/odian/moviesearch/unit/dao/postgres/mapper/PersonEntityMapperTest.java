package com.odian.moviesearch.unit.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Keyword;
import com.odian.moviesearch.core.domain.model.Media;
import com.odian.moviesearch.core.domain.model.Person;
import com.odian.moviesearch.dao.postgres.entity.PersonEntity;
import com.odian.moviesearch.dao.postgres.mapper.PersonEntityMapper;
import com.odian.moviesearch.unit.util.PersonUtils;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonEntityMapperTest {

    private static final PersonEntityMapper subject = Mappers.getMapper(PersonEntityMapper.class);

    @Test
    public void testEntityToDomain () {
        PersonEntity entity = PersonUtils.getPersonEntity();
        var expected = new Person(
                entity.getId(),
                entity.getSlug(),
                entity.getName(),
                entity.getBiography(),
                new Media(entity.getMediaEntity().getId(),
                        entity.getMediaEntity().getUrl(),
                        entity.getMediaEntity().getMediaType()),
                entity.getKeywords().stream()
                        .map(t -> new Keyword(t.getId(), t.getName()))
                        .collect(Collectors.toSet())
        );
        assertEquals(expected, subject.entityToDomain(entity));
    }
}
