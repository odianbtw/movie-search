package com.odian.moviesearch.unit.dao.postgres.mapper;

import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.dao.postgres.mapper.CountryEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.CountryEntityMapperImpl;
import com.odian.moviesearch.dao.postgres.model.CountryEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryEntityMapperTest {

    private final CountryEntityMapper subject = new CountryEntityMapperImpl();

    @Test
    void testEntityToCounty () {
        CountryEntity entity = new CountryEntity();
        entity.setId(1);
        entity.setName("USA");
        Country country = new Country(1, "USA");
        assertEquals(country, subject.to(entity));
    }

    @Test
    void testListEntityToListCompany () {
        CountryEntity entity = new CountryEntity();
        entity.setId(1);
        entity.setName("USA");
        var entityList = List.of(entity);
        var countryList = List.of(new Country(1, "USA"));
        assertEquals(countryList, subject.to(entityList));
    }

    @Test
    void testCountryToEntity () {
        CountryEntity entity = new CountryEntity();
        entity.setId(1);
        Country country = new Country(1, null);
        assertEquals(entity, subject.to(country));
    }

}
