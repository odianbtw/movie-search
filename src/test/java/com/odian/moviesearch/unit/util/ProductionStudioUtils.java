package com.odian.moviesearch.unit.util;

import com.odian.moviesearch.core.domain.model.ProductionStudio;
import com.odian.moviesearch.dao.postgres.entity.ProductionStudioEntity;

import java.time.Instant;
import java.util.UUID;

public class ProductionStudioUtils {
    public static ProductionStudioEntity getStudioEntity () {
        return new ProductionStudioEntity(
                UUID.randomUUID(),
                "warner-brothers",
                "Warner Brothers",
                Instant.now(),
                Instant.now()
        );
    }

    public static ProductionStudio getStudio () {
        return new ProductionStudio(
                UUID.randomUUID(),
                "slug",
                "Name"
        );
    }
}
