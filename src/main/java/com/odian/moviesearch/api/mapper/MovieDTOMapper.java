package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.MovieRequestDTO;
import com.odian.moviesearch.core.domain.model.*;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovieDTOMapper {
    default Movie dtoToDomain (MovieRequestDTO movieRequest) {
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
}
