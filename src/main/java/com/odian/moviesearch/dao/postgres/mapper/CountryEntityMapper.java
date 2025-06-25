package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.dao.postgres.model.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryEntityMapper {
    Country to (CountryEntity country);
    List<Country> to (List<CountryEntity> countries);
    CountryEntity to (Country country);
}
