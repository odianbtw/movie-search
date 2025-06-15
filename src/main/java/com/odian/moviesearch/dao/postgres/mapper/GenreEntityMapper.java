package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.model.Genre;
import com.odian.moviesearch.dao.postgres.model.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreEntityMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Genre to (GenreEntity genre);
    List<Genre> to(List<GenreEntity> genres);
}
