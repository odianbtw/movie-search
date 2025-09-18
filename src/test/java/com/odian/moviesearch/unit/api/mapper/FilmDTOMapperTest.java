package com.odian.moviesearch.unit.api.mapper;

import com.odian.moviesearch.api.mapper.*;
import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.domain.model.*;
import com.odian.moviesearch.unit.util.FilmUtils;
import org.junit.jupiter.api.Test;


import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilmDTOMapperTest {

    private final static CountryDTOMapper countryDTOMapper = mock(CountryDTOMapper.class);
    private final static GenreDTOMapper genreDTOMapper = mock(GenreDTOMapper.class);
    private final static KeywordDTOMapper keywordDTOMapper = mock(KeywordDTOMapper.class);
    private final static LanguageDTOMapper languageDTOMapper = mock(LanguageDTOMapper.class);
    private final static PersonDTOMapper personDTOMapper = mock(PersonDTOMapper.class);
    private final static ProductionStudioDTOMapper productionStudioDTOMapper = mock(ProductionStudioDTOMapper.class);
    private final static FilmDTOMapper subject = new FilmDTOMapperImpl(
            countryDTOMapper,
            genreDTOMapper,
            keywordDTOMapper,
            languageDTOMapper,
            personDTOMapper,
            productionStudioDTOMapper
    );

    @Test
    public void testDomainToDtoStatistics () {
        Statistics statistics = new Statistics(
                9.3,
                423_234,
                83.3f,
                51.5f
        );
        StatisticsDTO result = subject.domainToDto(statistics);
        assertNotNull(result);
        assertEquals(9.3, result.rating());
    }

    @Test
    public void testDomainToDtoExternalLinks () {
        ExternalLinks externalLinks = new ExternalLinks(
                "imdb.com",
                "tmdb.com"
        );
        ExternalLinksDTO result = subject.domainToDto(externalLinks);
        assertNotNull(result);
        assertEquals("imdb.com", result.imdbUrl());
    }

    @Test
    public void testMapPoster () {
        FilmDetails details = FilmUtils.getFilmDetails();
        details.setMedias(Set.of(
                new Media(UUID.randomUUID(), "some.url", MediaType.POSTER)
        ));
        String result = subject.mapPoster(details);
        assertEquals("some.url", result);
    }

    @Test
    public void testMapBackdrop () {
        FilmDetails details = FilmUtils.getFilmDetails();
        details.setMedias(Set.of(
                new Media(UUID.randomUUID(), "some.url", MediaType.BACKDROP)
        ));
        String result = subject.mapBackdrop(details);
        assertEquals("some.url", result);
    }

    @Test
    public void testMapTrailer () {
        FilmDetails details = FilmUtils.getFilmDetails();
        details.setMedias(Set.of(
                new Media(UUID.randomUUID(), "some.url", MediaType.TRAILER)
        ));
        String result = subject.mapTrailer(details);
        assertEquals("some.url", result);
    }


    @Test
    public void testDomainToDtoFilmDetails () {
        FilmDetails details = FilmUtils.getFilmDetails();
        when(personDTOMapper.domainToItemsDto(any())).thenReturn(Set.of(
                new NamedPersonItemDTO(UUID.randomUUID(), "Jack")
        ));
        when(genreDTOMapper.domainToDto(any(Genre.class))).thenReturn(new GenreDTO(1, "Thriller"));
        when(countryDTOMapper.domainToDto(any(Country.class))).thenReturn(new CountryDTO(1, "USA"));
        when(productionStudioDTOMapper.domainToDto(any(ProductionStudio.class))).thenReturn(new StudioDTO(UUID.randomUUID(), "a24-dsadsa","A24"));
        when(languageDTOMapper.domainToDto(any(Language.class))).thenReturn(new LanguageDTO(1, "English"));
        when(keywordDTOMapper.domainToDto(any(Keyword.class))).thenReturn(new KeywordDTO(UUID.randomUUID(), "pros"));
        FilmDetailsDTO result = subject.domainToDto(details);
        assertNotNull(result);
        assertEquals(details.getTagline(), result.tagline());
        assertEquals(details.getPoster().isEmpty(), result.filmMedia().posterUrl() == null);
        assertEquals(details.getStatistics().getRating(), result.statistics().rating());
        assertNotNull(result.directors());
        assertNotNull(result.genres());
        assertNotNull(result.countries());
        assertNotNull(result.studios());
        assertNotNull(result.languages());
        assertNotNull(result.keywords());
    }


    @Test
    public void testDtoToDomain () {
        FilmCreateRequest request = FilmUtils.getFilmCreateRequest();
        when(PersonDTOMapper.createById(any(UUID.class))).thenReturn(new Person(){{
            this.setId(UUID.randomUUID());
            this.setName("some");
        }});
        when(GenreDTOMapper.createById(anyInt()))
                .thenReturn(new Genre(ThreadLocalRandom.current().nextInt(), "ds"));
        when(CountryDTOMapper.createById(anyInt()))
                .thenReturn(new Country(ThreadLocalRandom.current().nextInt(), "ds"));
        when(GenreDTOMapper.createById(anyInt()))
                .thenReturn(new Genre(ThreadLocalRandom.current().nextInt(), "ds"));
        when(LanguageDTOMapper.createById(anyInt()))
                .thenReturn(new Language(ThreadLocalRandom.current().nextInt(), "ds"));
        when(ProductionStudioDTOMapper.createById(any(UUID.class)))
                .thenReturn(new ProductionStudio(UUID.randomUUID(), "d", "ds"));
        when(KeywordDTOMapper.dtoToDomainStatic(any(KeywordDTO.class)))
                .thenReturn(new Keyword(UUID.randomUUID(), "ds"));
        Film result = subject.dtoToDomain(request);
        assertNotNull(result);
        assertEquals(request.name(), result.getName());
        assertNotNull(result.getDetails());
        assertEquals(request.countryIds().size(), result.getDetails().getDirectors().size());
    }




}
