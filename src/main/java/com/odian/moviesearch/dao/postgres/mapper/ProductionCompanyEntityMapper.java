package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.ProductionCompany;
import com.odian.moviesearch.dao.postgres.entity.ProductionCompanyEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {CountryEntityMapper.class, MediaEntityMapper.class})
public interface ProductionCompanyEntityMapper {

    ProductionCompanyEntity domainToEntity (ProductionCompany company);
    ProductionCompany entityToDomain (ProductionCompanyEntity entity);
}
