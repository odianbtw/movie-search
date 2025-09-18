package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Media;
import com.odian.moviesearch.core.domain.model.MediaType;
import com.odian.moviesearch.dao.postgres.entity.EssentialFilmMediaEntity;
import com.odian.moviesearch.dao.postgres.entity.MediaEntity;
import org.mapstruct.Mapper;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface MediaEntityMapper {
    default Set<Media> entityToMedias (EssentialFilmMediaEntity entity) {
        if (entity == null) return Set.of();
        return Stream.of(entity.getPoster(), entity.getBackdrop(), entity.getTrailer())
                .filter(Objects::nonNull)
                .map(this::entityToDomain)
                .collect(Collectors.toSet());
    }
    Media entityToDomain (MediaEntity entity);
    MediaEntity domainToEntity (Media media);
    Set<MediaEntity> domainToEntity (Set<Media> medias);
    Set<Media> entityToDomain (Set<MediaEntity> entities);
}
