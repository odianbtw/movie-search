package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.ExternalLinksDTO;
import com.odian.moviesearch.api.model.FilmDTO;
import com.odian.moviesearch.core.domain.model.Film;
import com.odian.moviesearch.core.domain.model.Media;
import com.odian.moviesearch.core.domain.model.MediaType;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class FilmDTOMapper {

    FilmDTO domainToDto (Film film) {
        FilmDTO.builder()
                .id(film.getId())
                .slug(film.getSlug())
                .name(film.getName())
                .originalName(film.getOriginalName())
                .externalUrls(
                        new ExternalLinksDTO(
                                film.getExternalLinks().getImdbUrl(),
                                film.getExternalLinks().getImdbUrl()
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
                .posterUrl(film.getDetails().getMedias()
                        .stream()
                        .filter(t -> Objects.equals(t.getMediaType(), MediaType.POSTER))
                        .map(Media::getUrl)
                        .findFirst().orElse(null)
                )
                .backdropUrl(film.getDetails().getMedias()
                        .stream()
                        .filter(t -> Objects.equals(t.getMediaType(), MediaType.BACKDROP))
                        .map(Media::getUrl)
                        .findFirst().orElse(null)
                )
                .trailerUrl(film.getDetails().getMedias()
                        .stream()
                        .filter(t -> Objects.equals(t.getMediaType(), MediaType.TRAILER))
                        .map(Media::getUrl)
                        .findFirst().orElse(null)
                )
    }
}
