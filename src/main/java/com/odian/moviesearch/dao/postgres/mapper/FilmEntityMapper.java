package com.odian.moviesearch.dao.postgres.mapper;


import com.odian.moviesearch.core.domain.model.*;
import com.odian.moviesearch.dao.postgres.entity.FilmContributionEntity;
import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;


// todo: FIX INJECTION PROBLEM

@Mapper(componentModel = "spring",
        uses = {MediaEntityMapper.class,
                PersonEntityMapper.class,
                GenreEntityMapper.class,
                CountryEntityMapper.class,
                ProductionStudioEntityMapper.class,
                LanguageEntityMapper.class,
                KeywordEntityMapper.class
        },
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class FilmEntityMapper {

    protected MediaEntityMapper mediaEntityMapper;
    protected PersonEntityMapper personEntityMapper;
    protected GenreEntityMapper genreEntityMapper;
    protected CountryEntityMapper countryEntityMapper;
    protected ProductionStudioEntityMapper productionStudioEntityMapper;
    protected LanguageEntityMapper languageEntityMapper;
    protected KeywordEntityMapper keywordEntityMapper;


    public Film entityToDomain(FilmEntity entity) {
        if (entity == null) return null;

        FilmDetails details = FilmDetails.builder()
                .tagline(entity.getTagline())
                .description(entity.getDescription())
                .releaseDate(entity.getReleaseDate())
                .runtime(entity.getRuntime())
                .statistics(buildStatistics(entity))
                .medias(buildMedias(entity))
                .directors(buildDirectors(entity))
                .genres(buildGenres(entity))
                .countries(buildCountries(entity))
                .studios(buildStudios(entity))
                .languages(buildLanguages(entity))
                .keywords(buildKeywords(entity))
                .build();

        return new Film(
                entity.getId(),
                entity.getSlug(),
                entity.getName(),
                entity.getOriginalName(),
                new ExternalLinks(entity.getImdbId(), entity.getTmdbId()),
                details
        );
    }

    // --- Helper methods (same as before) ---
    private Statistics buildStatistics(FilmEntity entity) {
        return new Statistics(
                entity.getRatings().getRating(),
                entity.getRatings().getAmountOfRatings(),
                entity.getPopularity().getRating(),
                entity.getTrending().getRating()
        );
    }

    private Set<Media> buildMedias(FilmEntity entity) {
        return Set.of(
                mediaEntityMapper.entityToDomain(entity.getEssentialMedia().getPoster()),
                mediaEntityMapper.entityToDomain(entity.getEssentialMedia().getBackdrop()),
                mediaEntityMapper.entityToDomain(entity.getEssentialMedia().getTrailer())
        );
    }

    private Set<Person> buildDirectors(FilmEntity entity) {
        return entity.getFilmContributions().stream()
                .filter(fc -> fc.getContributorType() == ContributorType.DIRECTOR)
                .map(FilmContributionEntity::getPerson)
                .map(personEntityMapper::entityToDomain)
                .collect(Collectors.toSet());
    }

    private Set<Genre> buildGenres(FilmEntity entity) {
        return entity.getGenres().stream()
                .map(genreEntityMapper::entityToDomain)
                .collect(Collectors.toSet());
    }

    private Set<Country> buildCountries(FilmEntity entity) {
        return entity.getCountries().stream()
                .map(countryEntityMapper::entityToDomain)
                .collect(Collectors.toSet());
    }

    private Set<ProductionStudio> buildStudios(FilmEntity entity) {
        return entity.getStudios().stream()
                .map(productionStudioEntityMapper::entityToDomain)
                .collect(Collectors.toSet());
    }

    private Set<Language> buildLanguages(FilmEntity entity) {
        return entity.getLanguages().stream()
                .map(languageEntityMapper::entityToDomain)
                .collect(Collectors.toSet());
    }

    private Set<Keyword> buildKeywords(FilmEntity entity) {
        return entity.getKeywords().stream()
                .map(keywordEntityMapper::entityToDomain)
                .collect(Collectors.toSet());
    }
}