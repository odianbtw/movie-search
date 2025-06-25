package com.odian.moviesearch.unit.api.mapper;

import com.odian.moviesearch.api.mapper.CompanyDTOMapper;
import com.odian.moviesearch.api.mapper.CompanyDTOMapperImpl;
import com.odian.moviesearch.api.mapper.CountryDTOMapper;
import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.core.model.Media;
import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.enums.MediaType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class CompanyDTOMapperTest {

    private final CountryDTOMapper countryDTOMapper = mock(CountryDTOMapper.class);
    private final CompanyDTOMapper subject = new CompanyDTOMapperImpl(countryDTOMapper);


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
                new Media(null, "https://some.com/test_logo.png", MediaType.LOGO));
        assertEquals (company, subject.to(companyRequest));
    }

    @Test
    void testCompanyToDTo () {
        Company company = new Company(
                1L,
                "A24",
                "Test",
                new Country(1, "USA"),
                new Media(228L, "https://some.com/test_logo.png", MediaType.LOGO)
        );

        CompanyDTO dto = new CompanyDTO(
                1L,
                "A24",
                "https://some.com/test_logo.png",
                new CountryDTO(1, "USA"),
                "Test"
        );
        when(countryDTOMapper.to(any(Country.class))).thenReturn(new CountryDTO(1, "USA"));
        assertEquals(dto, subject.to(company));
    }

    @Test
    void testDtoToCompany () {
        CompanyUpdateDTO dto = new CompanyUpdateDTO(
                1L,
                "A24",
                "https://some.com/test_logo.png",
                1,
                "Test"
        );
        Company company = new Company(
                1L,
                "A24",
                "Test",
                new Country(1, null),
                new Media(null, "https://some.com/test_logo.png", MediaType.LOGO)
        );
        assertEquals(company, subject.to(dto));
    }

    @Test
    void testCompanyToItem () {
        Company company = new Company(
                1L,
                "A24",
                "Test",
                new Country(1, null),
                new Media(null, "https://some.com/test_logo.png", MediaType.LOGO)
        );
        CompanyItemDTO item = new CompanyItemDTO(
                1L,
                "A24",
                "https://some.com/test_logo.png"
        );
    }

    @Test
    void testPagedToPagedDto () {
        Company company = new Company(
                1L,
                "A24",
                "Test",
                new Country(1, "USA"),
                new Media(228L, "https://some.com/test_logo.png", MediaType.LOGO));
        PagedResponse<Company> paged = new PagedResponse<>(
                29L,
                3,
                3,
                5,
                List.of(company)
        );
        CompanyItemDTO item = new CompanyItemDTO(
                1L,
                "A24",
                "https://some.com/test_logo.png"
        );
        PagedResponseDTO<CompanyItemDTO> pagedDto = new PagedResponseDTO<>(
                29L,
                3,
                3,
                5,
                List.of(item)
        );
        assertEquals(pagedDto, subject.to(paged));
    }



}
