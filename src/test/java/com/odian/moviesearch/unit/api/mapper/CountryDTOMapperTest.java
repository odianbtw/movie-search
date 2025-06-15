package com.odian.moviesearch.unit.api.mapper;


import com.odian.moviesearch.api.model.CountryDTO;
import com.odian.moviesearch.core.model.Country;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryDTOMapperTest {

    private final CountryDTOMapper subject = new CountryDTOMapperImpl();

    @Test
    public void countryToCountryDTO () {
        Country country = new Country(1, "Germany");
        CountryDTO dto = new CountryDTO(1, "Germany");
        assertEquals(dto, subject.to(country));
    }

    @Test
    public void countryListToDTOs () {
        var list = List.of(new Country(1, "Germany"));
        var dtos = List.of(new CountryDTO(1, "Germany"));
        assertEquals(dtos, subject.to(list));
    }

}
