package com.odian.moviesearch.dao.postgres.mapper;


import com.odian.moviesearch.core.domain.model.Film;
import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FilmEntityMapper {

    public Film entityToDomain (FilmEntity entity) {
        return null;
    }
}
