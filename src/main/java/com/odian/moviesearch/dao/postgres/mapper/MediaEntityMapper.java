package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Media;
import com.odian.moviesearch.dao.postgres.entity.EssentialFilmMediaEntity;
import com.odian.moviesearch.dao.postgres.entity.MediaEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MediaEntityMapper {
    default Set<Media> entityToDomain (EssentialFilmMediaEntity entity) {
        return Set.of(
                entityToDomain(entity.getPoster()),
                entityToDomain(entity.getBackdrop()),
                entityToDomain(entity.getTrailer())
        );
    }
    Media entityToDomain (MediaEntity entity);
    Set<Media> entityToDomain (Set<MediaEntity> entities);
}
