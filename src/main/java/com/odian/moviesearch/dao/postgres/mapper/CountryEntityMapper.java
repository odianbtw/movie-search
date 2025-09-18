package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Country;
import com.odian.moviesearch.dao.postgres.entity.CountryEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CountryEntityMapper {
    Country entityToDomain (CountryEntity entity);
    Set<Country> entityToDomain (Set<CountryEntity> entities);
    CountryEntity domainToEntity (Country country);
}
