package com.odian.moviesearch.unit.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Keyword;
import com.odian.moviesearch.dao.postgres.entity.KeywordEntity;
import com.odian.moviesearch.dao.postgres.mapper.KeywordEntityMapper;
import com.odian.moviesearch.unit.util.KeywordUtils;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeywordEntityMapperTest {

    private static final KeywordEntityMapper subject = Mappers.getMapper(KeywordEntityMapper.class);

    @Test
    public void testEntityToDomain() {
        KeywordEntity entity = KeywordUtils.getKeywordEntity();
        Keyword expected = new Keyword(entity.getId(), entity.getName());
        assertEquals(expected, subject.entityToDomain(entity));
    }
}
