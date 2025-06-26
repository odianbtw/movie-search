package com.odian.moviesearch.unit.api.mapper;

import com.odian.moviesearch.api.mapper.*;
import com.odian.moviesearch.api.model.MovieRequest;
import com.odian.moviesearch.core.model.*;
import com.odian.moviesearch.core.model.enums.MediaType;
import com.odian.moviesearch.core.model.enums.MovieRating;
import com.odian.moviesearch.core.model.enums.MovieType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieDTOMapperTest {

    private final CountryDTOMapper countryDTOMapper = new CountryDTOMapperImpl();
    private final GenreDTOMapper genreDTOMapper = new GenreDTOMapperImpl();
    private final CompanyDTOMapper companyDTOMapper = new CompanyDTOMapperImpl(countryDTOMapper);
    private final MovieDTOMapper subject = new MovieDTOMapperImpl(countryDTOMapper, genreDTOMapper, companyDTOMapper);



    @Test
    void testMovieRequestToMovie () {
        MovieRequest request = MovieRequest.builder()
                .name("Fight Club")
                .slogan("Don't talk about fight club")
                .description("Test")
                .genreIds(List.of(4))
                .countryIds(List.of(1))
                .companyIds(List.of(3L))
                .coverUrl("https://some")
                .trailerUrl("https://some")
                .releaseDate(LocalDate.of(1999, 5, 11))
                .durationTime(140)
                .movieType(MovieType.MOVIE)
                .movieRating(MovieRating.R)
                .build();
        var company = new Company();
        company.setId(3L);
        Movie expected = Movie.builder()
                .name("Fight Club")
                .slogan("Don't talk about fight club")
                .description("Test")
                .genres(List.of(new Genre(4, null)))
                .countries(List.of(new Country(1, null)))
                .companies(List.of(company))
                .score(0.0f)
                .medias(List.of(
                        new Media(null, "https://some", MediaType.COVER),
                        new Media(null, "https://some", MediaType.TRAILER)
                ))
                .releaseDate(LocalDate.of(1999, 5, 11))
                .durationTime(140)
                .movieType(MovieType.MOVIE)
                .movieRating(MovieRating.R)
                .build();
        assertEquals(expected, subject.to(request));
    }

//    @Test
//    void testMovieToMovieDto () {
//        Company company =
//                new Company(1L, "WB", "tes", null, new Media(1L, "http://me", MediaType.LOGO));
//        Movie movie = Movie.builder()
//                .id(20L)
//                .name("Fight")
//                .description("ds")
//                .score(0.0f)
//                .description("Yes")
//                .genres(List.of(new Genre(1, "Th")))
//                .countries(List.of(new Country(1, "USA")))
//                .companies(List.of(company))
//                .medias()
//    }
}
