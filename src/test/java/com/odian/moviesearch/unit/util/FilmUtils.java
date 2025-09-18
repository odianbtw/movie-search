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

    public static FilmCreateRequest getFilmCreateRequest () {
        return FilmCreateRequest
                .builder()
                .name("Some")
                .originalName("Some")
                .externalLinks(
                        new ExternalLinksDTO("imdb.com", "tmdb.com")
                )
                .tagline("s")
                .description("s")
                .releaseDate(LocalDate.MIN)
                .runtime(122)
                .genreIds(Set.of(1))
                .countryIds(Set.of(1))
                .languageIds(Set.of(1))
                .studioIds(Set.of(UUID.randomUUID()))
                .directorIds(Set.of(UUID.randomUUID()))
                .keywords(Set.of(new KeywordDTO(UUID.randomUUID(), "prison")))
                .posterUrl("some")
                .backdropUrl("some")
                .trailerUrl("some")
                .build();
    }

    public static FilmDTO createDto(Film film) {
        var details = FilmDetailsDTO.builder()
                .tagline(film.getDetails().getTagline())
                .description(film.getDetails().getDescription())
                .releaseDate(film.getDetails().getReleaseDate())
                .runtime(film.getDetails().getRuntime())
                .statistics(new StatisticsDTO(
                        film.getDetails().getStatistics().getRating(),
                        film.getDetails().getStatistics().getAmountOfReviews(),
                        film.getDetails().getStatistics().getPopularity(),
                        film.getDetails().getStatistics().getTrending()
                ))
                .filmMedia(new EssentialFilmMediaDTO(
                        film.getDetails().getPoster().toString(),
                        film.getDetails().getBackdropImage().toString(),
                        film.getDetails().getTrailer().toString()
                ))
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
                                film.getDetails().getStudios().iterator().next().getSlug(),
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
        return new FilmDTO(
                film.getId(),
                film.getSlug(),
                film.getName(),
                film.getOriginalName(),
                new ExternalLinksDTO(
                        film.getExternalLinks().getImdbUrl(),
                        film.getExternalLinks().getTmdbUrl()
                ),
                details
        );

    }

    public static FilmEntity getFilmEntity() {
        FilmEntity film = FilmEntity.builder()
                .id(UUID.randomUUID())
                .slug("random")
                .originalName("Random")
                .imdbUrl("imdb.com")
                .tmdbUrl("tmdb.com")
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
                .statistics(getStatistics())
                .medias(Set.of(MediaUtils.getMedia()))
                .directors(Set.of(PersonUtils.getPerson()))
                .genres(Set.of(GenreUtils.getGenre()))
                .countries(Set.of(CountryUtils.getCountry()))
                .languages(Set.of(LanguageUtils.getLanguage()))
                .studios(Set.of(ProductionStudioUtils.getStudio()))
                .keywords(Set.of(KeywordUtils.getKeyword()))
                .build();
    }

    public static Statistics getStatistics () {
        return new Statistics(
                9.3,
                423_234,
                83.3f,
                51.5f
        );
    }

    public static FilmDetailsDTO getFilmDetailsDto (FilmDetails filmDetails) {
        return FilmDetailsDTO.builder()
                .tagline(filmDetails.getTagline())
                .description(filmDetails.getDescription())
                .releaseDate(filmDetails.getReleaseDate())
                .runtime(filmDetails.getRuntime())
                .statistics(getStatisticsDtoFromDomain(filmDetails.getStatistics()))
                .filmMedia(MediaUtils.getEssentialFilmMediaDTO(filmDetails))
                .directors(PersonUtils.getNamedPersonsItemDto(filmDetails.getDirectors()))
                .genres(GenreUtils.getGenresDto(filmDetails.getGenres()))
                .countries(null)
                .studios(null)
                .languages(null)
                .keywords(null)
                .build();
    }


    public static ExternalLinks getExternalLinks () {
        return new ExternalLinks(
                "imdb.com",
                "tmdb.com"
        );
    }

    public static ExternalLinksDTO getExternalLinksDto (ExternalLinks links) {
        return new ExternalLinksDTO(
                links.getImdbUrl(),
                links.getImdbUrl()
        );
    }

    public static StatisticsDTO getStatisticsDtoFromDomain(Statistics stats) {
        return new StatisticsDTO(
                stats.getRating(),
                stats.getAmountOfReviews(),
                stats.getPopularity(),
                stats.getTrending()
        );
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
