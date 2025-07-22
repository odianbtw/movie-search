package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.ProductionCompany;
import com.odian.moviesearch.dao.postgres.entity.ProductionCompanyEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {CountryEntityMapper.class, MediaEntityMapper.class})
public interface ProductionCompanyEntityMapper {
    ProductionCompany entityToDomain (ProductionCompanyEntity entity);
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductionCompanyEntity domainToEntity (ProductionCompany company);
    @Mapping(target = "medias", ignore = true)
    @Mapping(target = "country", ignore = true)
    ProductionCompany entityToDomainWithoutInfo (ProductionCompanyEntity entity);
}
