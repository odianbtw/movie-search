package com.odian.moviesearch.unit.api.mapper;

import com.odian.moviesearch.api.mapper.CountryDTOMapper;
import com.odian.moviesearch.api.model.CountryDTO;
import com.odian.moviesearch.core.domain.model.Country;
import com.odian.moviesearch.unit.util.CountryUtils;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryDTOMapperTest {

    private static final CountryDTOMapper subject = Mappers.getMapper(CountryDTOMapper.class);

    @Test
    public void testDomainToDto () {
        Country country = CountryUtils.getCountry();
        CountryDTO expected = new CountryDTO(country.getId(), country.getName());
        assertEquals(expected, subject.domainToDto(country));
    }
}
