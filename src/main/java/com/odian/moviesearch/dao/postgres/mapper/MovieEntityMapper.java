package com.odian.moviesearch.dao.postgres.mapper;


import com.odian.moviesearch.core.domain.model.Movie;
import com.odian.moviesearch.dao.postgres.entity.MovieInfoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieEntityMapper {
    default MovieInfoEntity domainToEntity (Movie movie) {
        MovieInfoEntity infoEntity = new MovieInfoEntity()
    }
}
