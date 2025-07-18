package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Country;
import com.odian.moviesearch.dao.postgres.entity.CountryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryEntityMapper {
    Country entityToDomain (CountryEntity entity);
    CountryEntity domainToEntity (Country country);
}
