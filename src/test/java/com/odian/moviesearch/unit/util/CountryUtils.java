package com.odian.moviesearch.unit.util;

import com.odian.moviesearch.core.domain.model.Country;
import com.odian.moviesearch.dao.postgres.entity.CountryEntity;

import java.time.Instant;

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
}
