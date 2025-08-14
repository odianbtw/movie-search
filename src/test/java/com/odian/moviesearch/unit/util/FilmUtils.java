package com.odian.moviesearch.unit.util;

import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.domain.model.*;
import com.odian.moviesearch.dao.postgres.entity.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class FilmUtils {

    public static Film getFilm () {
        return new Film(
                UUID.randomUUID(),
                "fight-club-1999-ads24d",
                "Fight Club",
                "Fight Club",
                new ExternalLinks(
                        "https://imdb.com/fight-club",
                        "https://tmdb.com/fight-club32344"
                ),
                getFilmDetails()
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

    public static FilmEntity getFilmEntity() {
        FilmEntity film = FilmEntity.builder()
                .id(UUID.randomUUID())
                .slug("random")
                .originalName("Random")
                .imdbId("imdb.com")
                .tmdbId("tmdb.com")
                .tagline("Something")
                .description("Some")
                .releaseDate(LocalDate.of(2000,1,1))
                .runtime(144)
                .genres(Set.of(GenreUtils.getGenreEntity()))
                .countries(Set.of(CountryUtils.getCountryEntity()))
                .studios(Set.of(ProductionStudioUtils.getStudioEntity()))
                .languages(Set.of(LanguageUtils.getLanguageEntity()))
                .keywords(Set.of(KeywordUtils.getKeywordEntity()))
                .build();

        film.setEssentialMedia(MediaUtils.getEssentialMediaEntity(film));
        film.setRatings(getFilmRatingEntity(film));
        film.setPopularity(getFilmPopularityEntity(film));
        film.setTrending(getFilmTrendingEntity(film));
        film.setFilmContributions(Set.of(FilmContributionUtils.getFilmContributionEntity(film, PersonUtils.getPersonEntity())));
        return film;
    }

    public static FilmDetails getFilmDetails () {
        return FilmDetails.builder()
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
                .medias(Set.of(MediaUtils.getMedia()))
                .directors(Set.of(PersonUtils.getPerson()))
                .genres(Set.of(GenreUtils.getGenre()))
                .countries(Set.of(CountryUtils.getCountry()))
                .languages(Set.of(LanguageUtils.getLanguage()))
                .studios(Set.of(ProductionStudioUtils.getStudio()))
                .keywords(Set.of(KeywordUtils.getKeyword()))
                .build();
    }

    private static FilmRatingsEntity getFilmRatingEntity (FilmEntity film) {
        return new FilmRatingsEntity(
                UUID.randomUUID(),
                film,
                9.6,
                323_321
        );
    }

    private static FilmPopularityEntity getFilmPopularityEntity (FilmEntity film) {
        return new FilmPopularityEntity(
               UUID.randomUUID(),
               film,
               63.2f,
               Instant.now()
        );
    }

    private static FilmTrendingEntity getFilmTrendingEntity (FilmEntity film) {
        return new FilmTrendingEntity(
                UUID.randomUUID(),
                film,
                63.2f,
                Instant.now()
        );
    }

}
