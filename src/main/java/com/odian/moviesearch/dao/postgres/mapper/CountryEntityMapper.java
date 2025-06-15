package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.dao.postgres.model.CountryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryEntityMapper {
    Country to (CountryEntity country);
    List<Country> to (List<CountryEntity> countries);
}
