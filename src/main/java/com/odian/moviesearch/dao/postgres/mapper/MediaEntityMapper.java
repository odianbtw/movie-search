package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.model.Media;
import com.odian.moviesearch.dao.postgres.model.MediaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MediaEntityMapper {
    MediaEntity to(Media media);
    Media map(MediaEntity media);
}
