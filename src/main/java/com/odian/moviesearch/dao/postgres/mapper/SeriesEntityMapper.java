package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.*;
import com.odian.moviesearch.dao.postgres.entity.MovieInfoEntity;
import com.odian.moviesearch.dao.postgres.entity.SeriesContentEntity;
import com.odian.moviesearch.dao.postgres.entity.SeriesInfoEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SeriesEntityMapper {

    @Autowired protected GenreEntityMapper genreEntityMapper;
    @Autowired protected CountryEntityMapper countryEntityMapper;
    @Autowired protected ProductionCompanyEntityMapper companyEntityMapper;
    @Autowired protected MediaEntityMapper mediaEntityMapper;

    public SeriesInfoEntity domainToEntity(Series series) {
        if (series == null) return null;
        return SeriesInfoEntity.builder()
                .imdbId(series.getTitleId().imdbId())
                .title(series.getTitle())
                .titleType(TitleType.SERIES)
                .genres(series.getTitleInfo().getGenres().stream()
                        .map(genreEntityMapper::domainToEntity)
                        .collect(Collectors.toSet()))
                .slogan(series.getTitleInfo().getSlogan())
                .description(series.getTitleInfo().getDescription())
                .ageRating(series.getTitleInfo().getAgeRating())
                .budget(series.getTitleInfo().getBudget())
                .revenue(series.getTitleInfo().getRevenue())
                .countries(series.getTitleInfo().getCountries().stream()
                        .map(countryEntityMapper::domainToEntity)
                        .collect(Collectors.toSet()))
                .companies(series.getTitleInfo().getProductionCompanies().stream()
                        .map(companyEntityMapper::domainToEntity)
                        .collect(Collectors.toSet()))
                .medias(series.getTitleInfo().getMedias().stream()
                        .map(mediaEntityMapper::domainToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

    public Series entityToDomain (SeriesInfoEntity entity) {
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
        return new Series(
                id,
                entity.getTitle(),
                info,
                entity.getScore().getScore()
        );
    }

    public SeriesContentEntity domainToEntity (Episode episode) {
        if (episode == null) return null;
        return SeriesContentEntity.builder()
                .episodeName(episode.getTitle())
                .description(episode.getDescription())
                .imdbId(episode.getId().imdbId())
                .seasonNumber(episode.getSeasonNumber())
                .episodeNumber(episode.getEpisodeNumber())
                .releaseDate(episode.getReleaseDate())
                .durationMinutes(episode.getDurationMinutes())
                .medias(episode.getMedias().stream()
                        .map(mediaEntityMapper::domainToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }


    public Episode entityToDomain (SeriesContentEntity seriesContent) {
        if (seriesContent == null) return null;
        Id id = new Id(seriesContent.getId(), seriesContent.getEpisodeName());
        return Episode.builder()
                .id(id)
                .seasonNumber(seriesContent.getSeasonNumber())
                .episodeNumber(seriesContent.getEpisodeNumber())
                .title(seriesContent.getEpisodeName())
                .description(seriesContent.getDescription())
                .releaseDate(seriesContent.getReleaseDate())
                .durationMinutes(seriesContent.getDurationMinutes())
                .score(seriesContent.getScore().getScore())
                .medias(seriesContent.getMedias().stream()
                        .map(mediaEntityMapper::entityToDomain)
                        .collect(Collectors.toSet()))
                .build();
    }

    public Set<Episode> entityToDomain (List<SeriesContentEntity> episodes) {
        return episodes.stream()
                .map(this::entityToDomain)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
