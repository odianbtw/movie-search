package com.odian.moviesearch.unit.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Media;
import com.odian.moviesearch.dao.postgres.entity.MediaEntity;
import com.odian.moviesearch.dao.postgres.mapper.MediaEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.MediaEntityMapperImpl;
import com.odian.moviesearch.unit.util.MediaUtils;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MediaEntityMapperTest {

    private static final MediaEntityMapper subject = new MediaEntityMapperImpl();

    @Test
    public void testEntityToDomain () {
        MediaEntity entity = MediaUtils.getMediaEntity();
        var expected = new Media(entity.getId(), entity.getUrl(), entity.getMediaType());
        assertEquals(expected, subject.entityToDomain(entity));
    }
}
