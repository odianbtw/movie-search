package com.odian.moviesearch.dao.postgres.mapper;


import com.odian.moviesearch.dao.postgres.entity.MovieInfoEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MovieEntityMapper {

    @Autowired protected GenreEntityMapper genreEntityMapper;
    @Autowired protected CountryEntityMapper countryEntityMapper;
    @Autowired protected ProductionCompanyEntityMapper companyEntityMapper;
    @Autowired protected MediaEntityMapper mediaEntityMapper;

    public MovieInfoEntity domainToEntity(Movie movie) {
        if (movie == null) return null;
        return MovieInfoEntity.builder()
                .imdbId(movie.getTitleId().imdbId())
                .title(movie.getTitle())
                .titleType(TitleType.MOVIE)
                .genres(movie.getTitleInfo().getGenres().stream()
                        .map(genreEntityMapper::domainToEntity)
                        .collect(Collectors.toSet()))
                .slogan(movie.getTitleInfo().getSlogan())
                .description(movie.getTitleInfo().getDescription())
                .releaseDate(movie.getReleaseDate())
                .durationMinutes(movie.getDurationMinutes())
                .ageRating(movie.getTitleInfo().getAgeRating())
                .budget(movie.getTitleInfo().getBudget())
                .revenue(movie.getTitleInfo().getRevenue())
                .countries(movie.getTitleInfo().getCountries().stream()
                        .map(countryEntityMapper::domainToEntity)
                        .collect(Collectors.toSet()))
                .companies(movie.getTitleInfo().getProductionCompanies().stream()
                        .map(companyEntityMapper::domainToEntity)
                        .collect(Collectors.toSet()))
                .medias(movie.getTitleInfo().getMedias().stream()
                        .map(mediaEntityMapper::domainToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }


    public Movie entityToDomain (MovieInfoEntity entity) {
        if (entity == null) return null;
        Id id = new Id(entity.getId(), entity.getImdbId());
        TitleInfo info = TitleInfo.builder()
                .slogan(entity.getSlogan())
                .description(entity.getDescription())
                .ageRating(entity.getAgeRating())
                .budget(entity.getBudget())
                .revenue(entity.getRevenue())
                .genres(entity.getGenres().stream()
                        .map(genreEntityMapper::entityToDomain)
                        .collect(Collectors.toSet()))
                .countries(entity.getCountries().stream()
                        .map(countryEntityMapper::entityToDomain)
                        .collect(Collectors.toSet()))
                .productionCompanies(entity.getCompanies().stream()
                        .map(companyEntityMapper::entityToDomainWithoutInfo)
                        .collect(Collectors.toSet()))
                .medias(entity.getMedias().stream()
                        .map(mediaEntityMapper::entityToDomain)
                        .collect(Collectors.toSet()))
                .build();
        return new Movie(
                id,
                entity.getTitle(),
                info,
                entity.getScore().getScore(),
                entity.getDurationMinutes(),
                entity.getReleaseDate()
        );
    }

    public Movie entityToDomainItem (MovieInfoEntity entity) {
        if (entity == null) return null;
        Id id = new Id(entity.getId(), entity.getImdbId());
        TitleInfo info = TitleInfo.builder()
                .slogan(entity.getSlogan())
                .description(entity.getDescription())
                .ageRating(entity.getAgeRating())
                .budget(entity.getBudget())
                .revenue(entity.getRevenue())
                .medias(entity.getMedias().stream()
                        .map(mediaEntityMapper::entityToDomain)
                        .collect(Collectors.toSet()))
                .build();
        return new Movie(
                id,
                entity.getTitle(),
                info,
                entity.getScore().getScore(),
                entity.getDurationMinutes(),
                entity.getReleaseDate()
        );
    }
}
