package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.domain.model.*;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public abstract class FilmDTOMapper {

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
                                .map(this::mapGenreToDTO)
                                .collect(Collectors.toSet())
                )
                .countries(
                        film.getDetails().getCountries().stream()
                                .map(this::mapCountryToDTO)
                                .collect(Collectors.toSet())
                )
                .studios(
                        film.getDetails().getStudios().stream()
                                .map(this::mapStudioToDTO)
                                .collect(Collectors.toSet())
                )
                .languages(
                        film.getDetails().getLanguages().stream()
                                .map(this::mapLanguageToDTO)
                                .collect(Collectors.toSet())
                )
                .keywords(
                        film.getDetails().getKeywords().stream()
                                .map(this::mapKeywordToDTO)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    // --- Helper mapping methods ---
    protected NamedPersonItemDTO mapPersonToNamedPersonItemDTO(Person person) {
        return new NamedPersonItemDTO(person.getId(), person.getName());
    }

    protected GenreDTO mapGenreToDTO(Genre genre) {
        return new GenreDTO(genre.getId(), genre.getName());
    }

    protected CountryDTO mapCountryToDTO(Country country) {
        return new CountryDTO(country.getId(), country.getName());
    }

    protected StudioDTO mapStudioToDTO(ProductionStudio studio) {
        return new StudioDTO(studio.getId(), studio.getName());
    }

    protected LanguageDTO mapLanguageToDTO(Language language) {
        return new LanguageDTO(language.getId(), language.getName());
    }

    protected KeywordDTO mapKeywordToDTO(Keyword keyword) {
        return new KeywordDTO(keyword.getId(), keyword.getName());
    }
}
