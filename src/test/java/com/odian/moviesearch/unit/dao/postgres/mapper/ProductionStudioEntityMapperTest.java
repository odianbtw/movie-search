package com.odian.moviesearch.unit.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.ProductionStudio;
import com.odian.moviesearch.dao.postgres.entity.ProductionStudioEntity;
import com.odian.moviesearch.dao.postgres.mapper.ProductionStudioEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.ProductionStudioEntityMapperImpl;
import com.odian.moviesearch.unit.util.ProductionStudioUtils;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductionStudioEntityMapperTest {

    private static final ProductionStudioEntityMapper subject = new ProductionStudioEntityMapperImpl();

    @Test
    public void testEntityToDomain() {
        ProductionStudioEntity entity = ProductionStudioUtils.getStudioEntity();
        ProductionStudio expected = new ProductionStudio(entity.getId(), entity.getSlug(), entity.getName());
        assertEquals(expected, subject.entityToDomain(entity));
    }
}
