package com.odian.moviesearch.unit.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Language;
import com.odian.moviesearch.dao.postgres.entity.LanguageEntity;
import com.odian.moviesearch.dao.postgres.mapper.LanguageEntityMapper;
import com.odian.moviesearch.unit.util.LanguageUtils;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageEntityMapperTest {

    private static final LanguageEntityMapper subject = Mappers.getMapper(LanguageEntityMapper.class);

    @Test
    public void testEntityToDomain() {
        LanguageEntity entity = LanguageUtils.getLanguageEntity();
        Language expected = new Language(entity.getId(), entity.getName());
        assertEquals(expected, subject.entityToDomain(entity));
    }
}
