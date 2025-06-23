package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.model.Media;
import com.odian.moviesearch.dao.postgres.model.MediaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MediaEntityMapper {
    MediaEntity to(Media media);
    Media map(MediaEntity media);
}
