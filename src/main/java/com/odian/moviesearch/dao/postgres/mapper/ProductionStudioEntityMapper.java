package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.ProductionStudio;
import com.odian.moviesearch.dao.postgres.entity.ProductionStudioEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductionStudioEntityMapper {
    ProductionStudio entityToDomain (ProductionStudioEntity entity);
    Set<ProductionStudio> entityToDomain (Set<ProductionStudioEntity> entities);
}
