package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Genre;
import com.odian.moviesearch.dao.postgres.entity.GenreEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface GenreEntityMapper {
    Genre entityToDomain (GenreEntity entity);
    Set<Genre> entityToDomain (Set<GenreEntity> entities);
}
