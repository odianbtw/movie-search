package com.odian.moviesearch.dao.postgres.mapper;


import com.odian.moviesearch.core.domain.model.*;
import com.odian.moviesearch.dao.postgres.entity.FilmContributionEntity;
import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class FilmEntityMapper {

    private final MediaEntityMapper mediaEntityMapper;
    private final PersonEntityMapper personEntityMapper;
    private final GenreEntityMapper genreEntityMapper;
    private final CountryEntityMapper countryEntityMapper;
    private final ProductionStudioEntityMapper productionStudioEntityMapper;
    private final LanguageEntityMapper languageEntityMapper;
    private final KeywordEntityMapper keywordEntityMapper;

    public Film entityToDomain (FilmEntity entity) {
        FilmDetails details = FilmDetails
                .builder()
                .tagline(entity.getTagline())
                .description(entity.getDescription())
                .releaseDate(entity.getReleaseDate())
                .runtime(entity.getRuntime())
                .statistics(new Statistics(
                        entity.getRatings().getRating(),
                        entity.getRatings().getAmountOfRatings(),
                        entity.getPopularity().getRating(),
                        entity.getTrending().getRating()
                ))
                .medias(
                        Set.of(
                                mediaEntityMapper.entityToDomain(entity.getEssentialMedia().getPoster()),
                                mediaEntityMapper.entityToDomain(entity.getEssentialMedia().getBackdrop()),
                                mediaEntityMapper.entityToDomain(entity.getEssentialMedia().getTrailer())
                        )
                )
                .directors(entity.getFilmContributions().stream()
                        .filter(t -> Objects.equals(t.getContributorType(), ContributorType.DIRECTOR))
                        .map(FilmContributionEntity::getPerson)
                        .map(personEntityMapper::entityToDomain)
                        .collect(Collectors.toSet()))
                .genres(entity.getGenres().stream()
                        .map(genreEntityMapper::entityToDomain)
                        .collect(Collectors.toSet()))
                .countries(entity.getCountries().stream()
                        .map(countryEntityMapper::entityToDomain)
                        .collect(Collectors.toSet()))
                .studios(entity.getStudios().stream()
                        .map(productionStudioEntityMapper::entityToDomain)
                        .collect(Collectors.toSet()))
                .languages(entity.getLanguages().stream()
                        .map(languageEntityMapper::entityToDomain)
                        .collect(Collectors.toSet()))
                .keywords(entity.getKeywords().stream()
                        .map(keywordEntityMapper::entityToDomain)
                        .collect(Collectors.toSet()))
                .build();
        return new Film(
                entity.getId(),
                entity.getSlug(),
                entity.getName(),
                entity.getOriginalName(),
                new ExternalLinks(
                        entity.getImdbId(),
                        entity.getTmdbId()
                ),
                details
        );

    }
}
