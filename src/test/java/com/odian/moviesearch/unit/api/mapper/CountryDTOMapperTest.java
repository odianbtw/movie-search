package com.odian.moviesearch.unit.api.mapper;

import com.odian.moviesearch.api.mapper.CountryDTOMapper;
import com.odian.moviesearch.api.model.CountryDTO;
import com.odian.moviesearch.core.model.Country;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryDTOMapperTest {
    private final CountryDTOMapper subject = Mappers.getMapper(CountryDTOMapper.class);

    @Test
    void testToCountryDTO() {
        Country country = new Country(1, "USA");
        CountryDTO countryDTO = new CountryDTO(1, "USA");
        assertEquals(countryDTO, subject.to(country));
    }

    @Test
    void testToCountryListDTO() {
        var list = List.of(new Country(1, "USA"));
        var dtoList = List.of(new CountryDTO(1, "USA"));
        assertEquals(dtoList, subject.to(list));
    }
}
