package com.odian.moviesearch.unit.dao.postgres.mapper;

import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.core.model.Media;
import com.odian.moviesearch.core.model.enums.MediaType;
import com.odian.moviesearch.dao.postgres.mapper.CountryEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.MediaEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.company.CompanyEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.company.CompanyEntityMapperImpl;
import com.odian.moviesearch.dao.postgres.model.CompanyEntity;
import com.odian.moviesearch.dao.postgres.model.CountryEntity;
import com.odian.moviesearch.dao.postgres.model.MediaEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompanyEntityMapperTest {
    private final CountryEntityMapper countryEntityMapper = mock(CountryEntityMapper.class);
    private final MediaEntityMapper mediaEntityMapper = mock(MediaEntityMapper.class);
    private final CompanyEntityMapper subject = new CompanyEntityMapperImpl(countryEntityMapper, mediaEntityMapper);

    @Test
    void testCompanyToEntity () {
        Company company = new Company(
                null,
                "A24",
                "Test",
                new Country(1, null),
                new Media(null, "https://some.com/test_logo.png", MediaType.LOGO)
        );
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(1);
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setUrl("https://some.com/test_logo.png");
        mediaEntity.setMediaType(MediaType.LOGO);
        CompanyEntity entity = new CompanyEntity();
        entity.setName("A24");
        entity.setDescription("Test");
        entity.setCountry(countryEntity);
        entity.setLogo(mediaEntity);
        when(countryEntityMapper.to(any(Country.class))).thenReturn(countryEntity);
        when(mediaEntityMapper.to(any(Media.class))).thenReturn(mediaEntity);
        assertEquals(entity, subject.to(company));
    }

    @Test
    void testEntityToCompany () {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(1);
        countryEntity.setName("USA");
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setId(234L);
        mediaEntity.setUrl("https://some.com/test_logo.png");
        mediaEntity.setMediaType(MediaType.LOGO);
        CompanyEntity entity = new CompanyEntity();
        entity.setId(23L);
        entity.setName("A24");
        entity.setDescription("Test");
        entity.setCountry(countryEntity);
        entity.setLogo(mediaEntity);
        Company company = new Company(
                23L,
                "A24",
                "Test",
                new Country(1, "USA"),
                new Media(234L, "https://some.com/test_logo.png", MediaType.LOGO)
        );
        when(countryEntityMapper.to(any(CountryEntity.class))).thenReturn(new Country(1, "USA"));
        when(mediaEntityMapper.to(any(MediaEntity.class))).thenReturn(new Media(234L, "https://some.com/test_logo.png", MediaType.LOGO));
        assertEquals(company, subject.to(entity));
    }

    @Test
    void testListEntityToListCompany () {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(1);
        countryEntity.setName("USA");
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setId(234L);
        mediaEntity.setUrl("https://some.com/test_logo.png");
        mediaEntity.setMediaType(MediaType.LOGO);
        CompanyEntity entity = new CompanyEntity();
        entity.setId(23L);
        entity.setName("A24");
        entity.setDescription("Test");
        entity.setCountry(countryEntity);
        entity.setLogo(mediaEntity);
        var entityList = List.of(entity);
        Company company = new Company(
                23L,
                "A24",
                "Test",
                new Country(1, "USA"),
                new Media(234L, "https://some.com/test_logo.png", MediaType.LOGO)
        );
        var companyList = List.of(company);
        when(countryEntityMapper.to(any(CountryEntity.class))).thenReturn(new Country(1, "USA"));
        when(mediaEntityMapper.to(any(MediaEntity.class))).thenReturn(new Media(234L, "https://some.com/test_logo.png", MediaType.LOGO));
        assertEquals(companyList, subject.to(entityList));
    }
}
