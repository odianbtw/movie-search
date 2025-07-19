package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Media;
import com.odian.moviesearch.dao.postgres.entity.MediaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MediaEntityMapper {
    MediaEntity domainToEntity (Media media);
}
