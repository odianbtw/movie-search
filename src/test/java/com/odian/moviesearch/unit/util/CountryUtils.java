package com.odian.moviesearch.unit.util;

import com.odian.moviesearch.api.model.CountryDTO;
import com.odian.moviesearch.core.domain.model.Country;
import com.odian.moviesearch.dao.postgres.entity.CountryEntity;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public class CountryUtils {
    public static CountryEntity getCountryEntity () {
        return new CountryEntity(
                1,
                "USA",
                Instant.now(),
                Instant.now()
        );
    }

    public static Country getCountry () {
        return new Country(
                1,
                "USA"
        );
    }

    public static CountryDTO getCountryDto (Country country) {
        return new CountryDTO(country.getId(), country.getName());
    }

    public static Set<CountryDTO> getCountriesDto (Set<Country> countries) {
        return countries.stream().map(CountryUtils::getCountryDto).collect(Collectors.toSet());
    }
}
