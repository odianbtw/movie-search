package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Media;
import com.odian.moviesearch.dao.postgres.entity.MediaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MediaEntityMapper {
    @Mapping(source = "mediaUri", target = "url")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    MediaEntity domainToEntity (Media media);
    @Mapping(source = "url", target = "mediaUri")
    Media entityToDomain (MediaEntity entity);
}
