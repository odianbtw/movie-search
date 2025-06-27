package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.model.Media;
import com.odian.moviesearch.dao.postgres.model.MediaEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MediaEntityMapper {
    MediaEntity to(Media media);
    Media to(MediaEntity media);
    List<Media> to (List<MediaEntity> media);
}
