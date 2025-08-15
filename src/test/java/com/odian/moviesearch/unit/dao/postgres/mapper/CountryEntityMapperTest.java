package com.odian.moviesearch.unit.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Country;
import com.odian.moviesearch.dao.postgres.entity.CountryEntity;
import com.odian.moviesearch.dao.postgres.mapper.CountryEntityMapper;
import com.odian.moviesearch.unit.util.CountryUtils;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryEntityMapperTest {

    private static final CountryEntityMapper subject = Mappers.getMapper(CountryEntityMapper.class);

    @Test
    public void testEntityToDomain () {
        CountryEntity country = CountryUtils.getCountryEntity();
        var expected = new Country(country.getId(), country.getName());
        assertEquals(expected, subject.entityToDomain(country));
    }
}
