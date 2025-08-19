package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.domain.model.*;
import lombok.AllArgsConstructor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring",
    uses = {GenreDTOMapper.class, CountryDTOMapper.class,
        ProductionStudioDTOMapper.class, LanguageDTOMapper.class,
        KeywordDTOMapper.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@AllArgsConstructor
public abstract class FilmDTOMapper {

    private GenreDTOMapper genreDTOMapper;
    private CountryDTOMapper countryDTOMapper;
    private ProductionStudioDTOMapper productionStudioDTOMapper;
    private LanguageDTOMapper languageDTOMapper;
    private KeywordDTOMapper keywordDTOMapper;

    public FilmDTO domainToDto(Film film) {
        return FilmDTO.builder()
                .id(film.getId())
                .slug(film.getSlug())
                .name(film.getName())
                .originalName(film.getOriginalName())
                .externalUrls(
                        new ExternalLinksDTO(
                                film.getExternalLinks().getImdbUrl(),
                                film.getExternalLinks().getTmdbUrl()
                        )
                )
                .tagline(film.getDetails().getTagline())
                .description(film.getDetails().getDescription())
                .releaseDate(film.getDetails().getReleaseDate())
                .runtime(film.getDetails().getRuntime())
                .rating(film.getDetails().getStatistics().getRating())
                .amountOfReviews(film.getDetails().getStatistics().getAmountOfReviews())
                .popularity(film.getDetails().getStatistics().getPopularity())
                .trending(film.getDetails().getStatistics().getTrending())
                .posterUrl(
                        film.getDetails().getPoster()
                                .map(Media::getUrl)
                                .orElse(null)
                )
                .backdropUrl(
                        film.getDetails().getBackdropImage()
                                .map(Media::getUrl)
                                .orElse(null)
                )
                .trailerUrl(
                        film.getDetails().getTrailer()
                                .map(Media::getUrl)
                                .orElse(null)
                )
                .directors(
                        film.getDetails().getDirectors().stream()
                                .map(this::mapPersonToNamedPersonItemDTO)
                                .collect(Collectors.toSet())
                )
                .genres(
                        film.getDetails().getGenres().stream()
                                .map(genreDTOMapper::domainToDto)
                                .collect(Collectors.toSet())
                )
                .countries(
                        film.getDetails().getCountries().stream()
                                .map(countryDTOMapper::domainToDto)
                                .collect(Collectors.toSet())
                )
                .studios(
                        film.getDetails().getStudios().stream()
                                .map(productionStudioDTOMapper::domainToDto)
                                .collect(Collectors.toSet())
                )
                .languages(
                        film.getDetails().getLanguages().stream()
                                .map(languageDTOMapper::domainToDto)
                                .collect(Collectors.toSet())
                )
                .keywords(
                        film.getDetails().getKeywords().stream()
                                .map(keywordDTOMapper::domainToDto)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public Film dtoCreateToDomain (FilmCreateRequest dto) {
        var details = FilmDetails.builder()
                .tagline(dto.tagline())
                .description(dto.description())
                .releaseDate(dto.releaseDate())
                .runtime(dto.runtime())
                .statistics(new Statistics(null,null,null,null))
                .medias(
                        Set.of(
                                new Media(null, dto.posterUrl(), MediaType.POSTER),
                                new Media(null, dto.backdropUrl(), MediaType.BACKDROP),
                                new Media(null, dto.trailerUrl(), MediaType.TRAILER)
                        )
                )
                .directors(dto.directorIds().stream()
                        .map(this::mapIdToPerson)
                        .collect(Collectors.toSet()))
                .genres(dto.genreIds().stream()
                        .map(genreDTOMapper::idToGenre)
                        .collect(Collectors.toSet()))
                .countries(dto.countryIds().stream()
                        .map(countryDTOMapper::idToDomain)
                        .collect(Collectors.toSet()))
                .languages(dto.languageIds().stream()
                        .map(languageDTOMapper::idToDomain)
                        .collect(Collectors.toSet()))
                .studios(dto.studioIds().stream()
                        .map(productionStudioDTOMapper::idToDomain)
                        .collect(Collectors.toSet()))
                .keywords(dto.keywords().stream()
                        .map(keywordDTOMapper::dtoToDomain)
                        .collect(Collectors.toSet()))
                .build();
        return new Film(
                null,
                null,
                dto.name(),
                dto.originalName(),
                new ExternalLinks(
                        dto.externalLinks().imdb(),
                        dto.externalLinks().tmdb()
                ),
                details
        );
    }



    private NamedPersonItemDTO mapPersonToNamedPersonItemDTO(Person person) {
        return new NamedPersonItemDTO(person.getId(), person.getName());
    }

    private Person mapIdToPerson (UUID id) {
        return new Person(
                id,
                null,
                null,
                null,
                null,
                null
        );
    }

}
