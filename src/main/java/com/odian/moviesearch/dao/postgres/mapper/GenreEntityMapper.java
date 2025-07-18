package com.odian.moviesearch.dao.postgres.mapper;


import com.odian.moviesearch.core.domain.model.Genre;
import com.odian.moviesearch.dao.postgres.entity.GenreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreEntityMapper {
    Genre entityToDomain (GenreEntity entity);
    GenreEntity domainToEntity (Genre genre);
}
