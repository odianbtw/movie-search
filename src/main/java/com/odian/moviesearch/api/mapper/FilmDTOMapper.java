package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring",
    uses = {GenreDTOMapper.class, CountryDTOMapper.class,
        ProductionStudioDTOMapper.class, LanguageDTOMapper.class,
        KeywordDTOMapper.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@RequiredArgsConstructor
public abstract class FilmDTOMapper {

    private final GenreDTOMapper genreDTOMapper;
    private final CountryDTOMapper countryDTOMapper;
    private final ProductionStudioDTOMapper productionStudioDTOMapper;
    private final LanguageDTOMapper languageDTOMapper;
    private final KeywordDTOMapper keywordDTOMapper;

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

    private NamedPersonItemDTO mapPersonToNamedPersonItemDTO(Person person) {
        return new NamedPersonItemDTO(person.getId(), person.getName());
    }

}
