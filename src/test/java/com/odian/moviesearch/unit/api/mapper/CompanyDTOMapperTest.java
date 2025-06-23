package com.odian.moviesearch.unit.api.mapper;

import com.odian.moviesearch.api.mapper.CompanyDTOMapper;
import com.odian.moviesearch.api.model.CompanyRequest;
import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.core.model.Media;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CompanyDTOMapperTest {
    private final CompanyDTOMapper subject = Mappers.getMapper(CompanyDTOMapper.class);


    @Test
    void testRequestToCompany() {
        CompanyRequest companyRequest = new CompanyRequest("A24",
                "https://some.com/test_logo.png",
                2,
                "Some");
        Company company = new Company(null,
                "A24",
                "Some",
                new Country(2, null),
                new Media(null, "https://some.com/test_logo.png", null));
        assertEquals (company, subject.to(companyRequest));

    }
}
