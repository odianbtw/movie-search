package com.odian.moviesearch.unit.api.mapper;

import com.odian.moviesearch.api.mapper.CompanyDTOMapper;
import com.odian.moviesearch.api.mapper.CountryDTOMapper;
import com.odian.moviesearch.api.model.CompanyDTO;
import com.odian.moviesearch.api.model.CompanyRequest;
import com.odian.moviesearch.api.model.CountryDTO;
import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.core.model.Media;
import com.odian.moviesearch.core.model.enums.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


// todo: get rid out of reflection shit
public class CompanyDTOMapperTest {

    private final CompanyDTOMapper subject = new CompanyDTOMapperImpl();

    @Test
    public void companyRequestToCompany () {
        CompanyRequest request = new CompanyRequest("A24",
                "https://moviesearch.media/logos/a24_logo.jpg",
                1,
                "Cool company");
        Country country = new Country();
        country.setId(1);
        Media media = new Media();
        media.setUrl("https://moviesearch.media/logos/a24_logo.jpg");
        Company expected = new Company(null, "A24", "Cool company", country, media);
        assertEquals(expected, subject.to(request));
    }

    @Test
    public void companyToCompanyDTO() throws NoSuchFieldException, IllegalAccessException {
        Country country = new Country(1, "Germany");
        Media media = new Media(23L, "A24_logo", "https://moviesearch.media/logos/a24_logo.jpg", MediaType.LOGO);
        Company company = new Company(254L, "A24", "Cool company", country, media);
        CompanyDTO expected = new CompanyDTO(254L, "A24", "https://moviesearch.media/logos/a24_logo.jpg",
                new CountryDTO(1, "Germany"), "Cool company");
        CountryDTOMapper countryDTOMapper = mock(CountryDTOMapper.class);
        when(countryDTOMapper.to(any(Country.class))).thenReturn(new CountryDTO(1, "Germany"));
        Field field = subject.getClass().getDeclaredField("countryDTOMapper");
        ReflectionUtils.makeAccessible(field);
        field.set(subject, countryDTOMapper);
        assertEquals(expected, subject.to(company));
    }
}
