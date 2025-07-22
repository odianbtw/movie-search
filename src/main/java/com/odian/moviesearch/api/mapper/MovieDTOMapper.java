package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.MovieDTO;
import com.odian.moviesearch.api.model.MovieRequestDTO;
import com.odian.moviesearch.core.domain.model.*;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MovieDTOMapper {

    @Autowired protected CountryDTOMapper countryDTOMapper;
    @Autowired protected GenreDTOMapper genreDTOMapper;
    @Autowired protected ProductionCompanyDTOMapper productionCompanyDTOMapper;

    public Movie dtoToDomain (MovieRequestDTO movieRequest) {
        if (movieRequest == null) return null;
        Id id = new Id(null, movieRequest.imdbId());
        Set<Media> medias = Set.of(
                new Media(null, movieRequest.coverUrl(), MediaType.COVER), new Media(null, movieRequest.trailerUrl(), MediaType.TRAILER)
        );
        TitleInfo info = TitleInfo.builder()
                .slogan(movieRequest.slogan())
                .description(movieRequest.description())
                .genres(movieRequest.genreIds().stream().map(i -> new Genre(i, null)).collect(Collectors.toSet()))
                .countries(movieRequest.countryIds().stream().map(i -> new Country(i, null)).collect(Collectors.toSet()))
                .productionCompanies(movieRequest.productionCompanyIds().stream().map(i -> new ProductionCompany(i, null, null, null, null)).collect(Collectors.toSet()))
                .ageRating(movieRequest.ageRating())
                .medias(medias)
                .budget(movieRequest.budget())
                .revenue(movieRequest.revenue()).build();

        return new Movie(
                id,
                movieRequest.title(),
                info,
                null,
                movieRequest.durationMinutes(),
                movieRequest.releaseDate()
        );
    }


    public MovieDTO domainToDto (Movie movie) {
        if (movie == null) return null;
        return MovieDTO.builder()
                .id(movie.getTitleId().id())
                .imdbId(movie.getTitleId().imdbId())
                .title(movie.getTitle())
                .slogan(movie.getTitleInfo().getSlogan())
                .description(movie.getTitleInfo().getDescription())
                .score(movie.getScore())
                .countries(movie.getTitleInfo().getCountries().stream()
                        .map(countryDTOMapper::domainToDto)
                        .collect(Collectors.toSet()))
                .genres(movie.getTitleInfo().getGenres().stream()
                        .map(genreDTOMapper::domainToDto)
                        .collect(Collectors.toSet()))
                .productionStudios(movie.getTitleInfo().getProductionCompanies().stream()
                        .map(productionCompanyDTOMapper::domainToShortDto)
                        .collect(Collectors.toSet()))
                .ageRating(movie.getTitleInfo().getAgeRating())
                .coverUrl(movie.getTitleInfo().getMedias().stream()
                        .filter(media -> Objects.equals(media.getMediaType(), MediaType.COVER))
                        .map(Media::getMediaUri)
                        .findFirst().orElse(null))
                .trailerUrl(movie.getTitleInfo().getMedias().stream()
                        .filter(media -> Objects.equals(media.getMediaType(), MediaType.TRAILER))
                        .map(Media::getMediaUri)
                        .findFirst().orElse(null))
                .budget(movie.getTitleInfo().getBudget())
                .revenue(movie.getTitleInfo().getRevenue())
                .durationMinutes(movie.getDurationMinutes())
                .releaseDate(movie.getReleaseDate())
                .build();
    }
}
