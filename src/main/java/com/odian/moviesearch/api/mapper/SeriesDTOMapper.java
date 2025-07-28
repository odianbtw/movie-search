package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.domain.model.*;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SeriesDTOMapper {

    @Autowired protected CountryDTOMapper countryDTOMapper;
    @Autowired protected GenreDTOMapper genreDTOMapper;
    @Autowired protected ProductionCompanyDTOMapper productionCompanyDTOMapper;

    public Series dtoToDomain (SeriesRequestDTO seriesRequest) {
        if (seriesRequest == null) return null;
        Id id = new Id(null, seriesRequest.imdbId());
        Set<Media> medias = Set.of(
                new Media(null, seriesRequest.coverUrl(), MediaType.COVER), new Media(null, seriesRequest.trailerUrl(), MediaType.TRAILER)
        );
        TitleInfo info = TitleInfo.builder()
                .slogan(seriesRequest.slogan())
                .description(seriesRequest.description())
                .genres(seriesRequest.genreIds().stream().map(i -> new Genre(i, null)).collect(Collectors.toSet()))
                .countries(seriesRequest.countryIds().stream().map(i -> new Country(i, null)).collect(Collectors.toSet()))
                .productionCompanies(seriesRequest.productionCompanyIds().stream().map(i -> new ProductionCompany(i, null, null, null, null)).collect(Collectors.toSet()))
                .ageRating(seriesRequest.ageRating())
                .budget(seriesRequest.budget())
                .revenue(seriesRequest.revenue())
                .medias(medias).build();

        return new Series(
                id,
                seriesRequest.title(),
                info,
                null
        );
    }

    public SeriesDTO domainToDto (Series series) {
        if (series == null) return null;
        return SeriesDTO.builder()
                .id(series.getTitleId().id())
                .imdbId(series.getTitleId().imdbId())
                .title(series.getTitle())
                .slogan(series.getTitleInfo().getSlogan())
                .description(series.getTitleInfo().getDescription())
                .score(series.getScore())
                .countries(series.getTitleInfo().getCountries().stream()
                        .map(countryDTOMapper::domainToDto)
                        .collect(Collectors.toSet()))
                .genres(series.getTitleInfo().getGenres().stream()
                        .map(genreDTOMapper::domainToDto)
                        .collect(Collectors.toSet()))
                .productionStudios(series.getTitleInfo().getProductionCompanies().stream()
                        .map(productionCompanyDTOMapper::domainToShortDto)
                        .collect(Collectors.toSet()))
                .ageRating(series.getTitleInfo().getAgeRating())
                .coverUrl(series.getTitleInfo().getMedias().stream()
                        .filter(media -> Objects.equals(media.getMediaType(), MediaType.COVER))
                        .map(Media::getMediaUri)
                        .findFirst().orElse(null))
                .trailerUrl(series.getTitleInfo().getMedias().stream()
                        .filter(media -> Objects.equals(media.getMediaType(), MediaType.TRAILER))
                        .map(Media::getMediaUri)
                        .findFirst().orElse(null))
                .budget(series.getTitleInfo().getBudget())
                .revenue(series.getTitleInfo().getRevenue())
                .build();
    }

    public Episode dtoRequestToDomain (EpisodeRequest request) {
        if(request == null) return null;
        Id id = new Id(null, request.imdbId());

        return new Episode(
                id,
                request.seasonNumber(),
                request.episodeNumber(),
                request.title(),
                request.description(),
                request.releaseDate(),
                request.durationMinutes(),
                null,
                Set.of(new Media(null, request.coverUrl(), MediaType.COVER))
        );
    }

    public EpisodeDTO domainToDto (Episode episode) {
        return EpisodeDTO.builder()
                .id(episode.getId().id())
                .imdbId(episode.getId().imdbId())
                .seasonNumber(episode.getSeasonNumber())
                .episodeNumber(episode.getEpisodeNumber())
                .title(episode.getTitle())
                .description(episode.getDescription())
                .releaseDate(episode.getReleaseDate())
                .durationMinutes(episode.getDurationMinutes())
                .coverUrl(episode.getMedias().stream()
                        .filter(t -> Objects.equals(t.getMediaType(), MediaType.COVER))
                        .map(Media::getMediaUri).findFirst().orElse(null))
                .build();
    }

    public SeriesItemDTO domainToItemDto (Series movie) {
        if (movie == null) return null;
        return new SeriesItemDTO (
                movie.getTitleId().id(),
                movie.getTitle(),
                movie.getTitleInfo().getMedias().stream()
                        .filter(media -> Objects.equals(media.getMediaType(), MediaType.COVER))
                        .map(Media::getMediaUri)
                        .findFirst().orElse(null),
                movie.getScore(),
                movie.getTitleInfo().getAgeRating()
        );
    }
}
