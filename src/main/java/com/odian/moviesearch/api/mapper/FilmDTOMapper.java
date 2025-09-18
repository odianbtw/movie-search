package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.domain.model.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Mapper(componentModel = "spring",
    uses = {
        CountryDTOMapper.class,
        GenreDTOMapper.class,
        KeywordDTOMapper.class,
        LanguageDTOMapper.class,
        PersonDTOMapper.class,
        ProductionStudioDTOMapper.class
    },
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FilmDTOMapper {
    StatisticsDTO domainToDto (Statistics domain);

    ExternalLinksDTO domainToDto (ExternalLinks domain);

    @Named("mapPoster")
    default String mapPoster(FilmDetails domain) {
        return domain.getPoster().map(Media::getUrl).orElse(null);
    }

    @Named("mapBackdrop")
    default String mapBackdrop(FilmDetails domain) {
        return domain.getBackdropImage().map(Media::getUrl).orElse(null);
    }

    @Named("mapTrailer")
    default String mapTrailer(FilmDetails domain) {
        return domain.getTrailer().map(Media::getUrl).orElse(null);
    }

    @Mapping(target = "filmMedia.posterUrl", source = "details", qualifiedByName = "mapPoster")
    @Mapping(target = "filmMedia.backdropUrl", source = "details", qualifiedByName = "mapBackdrop")
    @Mapping(target = "filmMedia.trailerUrl", source = "details", qualifiedByName = "mapTrailer")
    @Mapping(target = "keywords", source = "keywords", qualifiedByName = "keywordToDto")
    FilmDetailsDTO domainToDto(FilmDetails details);

    @Mapping(target = "posterUrl", source = "film.details", qualifiedByName = "mapPoster")
    @Mapping(target = "statistics", source = "film.details.statistics")
    @Mapping(target = "description", source = "film.details.description")
    @Mapping(target = "releaseDate", source = "film.details.releaseDate")
    FilmItemDTO domainToShortDto (Film film);


    default Film dtoToDomain(FilmCreateRequest filmCreateRequest) {
        FilmDetails details = FilmDetails.builder()
                .tagline(filmCreateRequest.tagline())
                .description(filmCreateRequest.description())
                .releaseDate(filmCreateRequest.releaseDate())
                .runtime(filmCreateRequest.runtime())
                .medias(Set.of(
                        new Media(null, filmCreateRequest.posterUrl(), MediaType.POSTER),
                        new Media(null, filmCreateRequest.backdropUrl(), MediaType.BACKDROP),
                        new Media(null, filmCreateRequest.trailerUrl(), MediaType.TRAILER)
                ))
                .directors(filmCreateRequest.directorIds().stream()
                        .map(PersonDTOMapper::createById)
                        .collect(toSet()))
                .genres(filmCreateRequest.genreIds().stream()
                        .map(GenreDTOMapper::createById)
                        .collect(toSet()))
                .countries(filmCreateRequest.countryIds().stream()
                        .map(CountryDTOMapper::createById)
                        .collect(toSet()))
                .languages(filmCreateRequest.languageIds().stream()
                        .map(LanguageDTOMapper::createById)
                        .collect(toSet()))
                .studios(filmCreateRequest.studioIds().stream()
                        .map(ProductionStudioDTOMapper::createById)
                        .collect(toSet()))
                .keywords(filmCreateRequest.keywords().stream()
                        .map(KeywordDTOMapper::dtoToDomainStatic)
                        .collect(toSet()))
                .build();
        return new Film(
                null,
                null,
                filmCreateRequest.name(),
                filmCreateRequest.originalName(),
                new ExternalLinks(filmCreateRequest.externalLinks().imdbUrl(),
                        filmCreateRequest.externalLinks().tmdbUrl()),
                details
        );
    }

    FilmDTO domainToDto(Film domain);

    default Film dtoToDomain(FilmUpdateRequest filmUpdateRequest) {
        FilmDetails details = FilmDetails.builder()
                .tagline(filmUpdateRequest.tagline())
                .description(filmUpdateRequest.description())
                .releaseDate(filmUpdateRequest.releaseDate())
                .runtime(filmUpdateRequest.runtime())
                .medias(Set.of(
                        new Media(null, filmUpdateRequest.posterUrl(), MediaType.POSTER),
                        new Media(null, filmUpdateRequest.backdropUrl(), MediaType.BACKDROP),
                        new Media(null, filmUpdateRequest.trailerUrl(), MediaType.TRAILER)
                ))
                .directors(filmUpdateRequest.directorIds().stream()
                        .map(PersonDTOMapper::createById)
                        .collect(toSet()))
                .genres(filmUpdateRequest.genreIds().stream()
                        .map(GenreDTOMapper::createById)
                        .collect(toSet()))
                .countries(filmUpdateRequest.countryIds().stream()
                        .map(CountryDTOMapper::createById)
                        .collect(toSet()))
                .languages(filmUpdateRequest.languageIds().stream()
                        .map(LanguageDTOMapper::createById)
                        .collect(toSet()))
                .studios(filmUpdateRequest.studioIds().stream()
                        .map(ProductionStudioDTOMapper::createById)
                        .collect(toSet()))
                .keywords(filmUpdateRequest.keywords().stream()
                        .map(KeywordDTOMapper::dtoToDomainStatic)
                        .collect(toSet()))
                .build();
        return new Film(
                filmUpdateRequest.id(),
                null,
                filmUpdateRequest.name(),
                filmUpdateRequest.originalName(),
                new ExternalLinks(filmUpdateRequest.externalLinks().imdbUrl(),
                        filmUpdateRequest.externalLinks().tmdbUrl()),
                details
        );
    }

}
