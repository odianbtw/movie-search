package com.odian.moviesearch.unit.util;

import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.domain.model.*;
import com.odian.moviesearch.dao.postgres.entity.FilmEntity;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class FilmUtils {

    public static Film createFilm () {
        UUID filmUUID = UUID.randomUUID();
        ExternalLinks externalLinks = new ExternalLinks(
                "https://imdb.com/fight-club",
                "https://tmdb.com/fight-club32344"
        );
        FilmDetails details = FilmDetails.builder()
                .tagline("We are the same person")
                .description("Something")
                .releaseDate(LocalDate.of(1999, 3,10))
                .runtime(144)
                .statistics(
                        new Statistics(
                                9.3f,
                                423_234,
                                83.3f,
                                51.5f
                        )
                )
                .medias(Set.of(
                        new Media(UUID.randomUUID(), "some.png", MediaType.POSTER),
                        new Media(UUID.randomUUID(), "some.png", MediaType.BACKDROP),
                        new Media(UUID.randomUUID(), "some.png", MediaType.TRAILER)
                ))
                .directors(Set.of(new Person(
                        UUID.randomUUID(),
                        "david-fincher",
                        "David Fincher",
                        null,
                        null,
                        null
                )))
                .genres(Set.of(
                        new Genre(2, "Thriller")
                ))
                .countries(
                        Set.of(
                                new Country(23, "USA")
                        )
                )
                .languages(
                        Set.of(new Language(1, "English"))
                )
                .studios(Set.of(
                        new ProductionStudio(UUID.randomUUID(), "warner-brothers-23dsf","Warner Brothers")
                ))
                .keywords(Set.of(
                        new Keyword(UUID.randomUUID(), "madness")
                ))
                .build();
        return new Film(
                filmUUID,
                "fight-club-1999-ads24d",
                "Fight Club",
                "Fight Club",
                externalLinks,
                details
        );
    }

    public static FilmEntity createEntity (Film film) {
        FilmEntity.builder()
                .id(film.getId())
                .slug(film.getSlug())
                .name(film.getName())
                .originalName(film.getOriginalName())
                .imdbId(film.getExternalLinks().getImdbUrl())
                .tmdbId(film.getExternalLinks().getTmdbUrl())
                .tagline(film.getDetails().getTagline());
        return null;
    }

    public static FilmDTO createDto(Film film) {
        return FilmDTO.builder()
                .id(film.getId())
                .slug(film.getSlug())
                .name(film.getName())
                .originalName(film.getOriginalName())
                .externalUrls(new ExternalLinksDTO(
                        film.getExternalLinks().getImdbUrl(),
                        film.getExternalLinks().getTmdbUrl()
                ))
                .tagline(film.getDetails().getTagline())
                .description(film.getDetails().getDescription())
                .releaseDate(film.getDetails().getReleaseDate())
                .runtime(film.getDetails().getRuntime())
                .rating(film.getDetails().getStatistics().getRating())
                .amountOfReviews(film.getDetails().getStatistics().getAmountOfReviews())
                .popularity(film.getDetails().getStatistics().getPopularity())
                .trending(film.getDetails().getStatistics().getTrending())
                .posterUrl(film.getDetails().getPoster().map(Media::getUrl).orElse(null))
                .backdropUrl(film.getDetails().getBackdropImage().map(Media::getUrl).orElse(null))
                .trailerUrl(film.getDetails().getTrailer().map(Media::getUrl).orElse(null))
                .directors(Set.of(
                        new NamedPersonItemDTO(
                                film.getDetails().getDirectors().iterator().next().getId(),
                                film.getDetails().getDirectors().iterator().next().getName()
                        )
                ))
                .genres(Set.of(
                        new GenreDTO(
                                film.getDetails().getGenres().iterator().next().getId(),
                                film.getDetails().getGenres().iterator().next().getName()
                        )
                ))
                .countries(Set.of(
                        new CountryDTO(
                                film.getDetails().getCountries().iterator().next().getId(),
                                film.getDetails().getCountries().iterator().next().getName()
                        )
                ))
                .studios(Set.of(
                        new StudioDTO(
                                film.getDetails().getStudios().iterator().next().getId(),
                                film.getDetails().getStudios().iterator().next().getName()
                        )
                ))
                .languages(Set.of(
                        new LanguageDTO(
                                film.getDetails().getLanguages().iterator().next().getId(),
                                film.getDetails().getLanguages().iterator().next().getName()
                        )
                ))
                .keywords(Set.of(
                        new KeywordDTO(
                                film.getDetails().getKeywords().iterator().next().getId(),
                                film.getDetails().getKeywords().iterator().next().getName()
                        )
                ))
                .build();
    }

}
