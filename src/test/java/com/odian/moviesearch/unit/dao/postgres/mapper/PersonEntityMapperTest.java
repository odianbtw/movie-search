package com.odian.moviesearch.unit.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Keyword;
import com.odian.moviesearch.core.domain.model.Media;
import com.odian.moviesearch.core.domain.model.Person;
import com.odian.moviesearch.dao.postgres.entity.MediaEntity;
import com.odian.moviesearch.dao.postgres.entity.PersonEntity;
import com.odian.moviesearch.dao.postgres.mapper.MediaEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.PersonEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.PersonEntityMapperImpl;
import com.odian.moviesearch.unit.util.PersonUtils;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonEntityMapperTest {

    private static final MediaEntityMapper mediaMapper = mock(MediaEntityMapper.class);
    private static final PersonEntityMapper subject = new PersonEntityMapperImpl(mediaMapper);

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
        when(mediaMapper.entityToDomain(any(MediaEntity.class))).thenReturn(new Media(entity.getMediaEntity().getId(),
                entity.getMediaEntity().getUrl(),
                entity.getMediaEntity().getMediaType()));
        assertEquals(expected, subject.entityToDomain(entity));
    }
}
