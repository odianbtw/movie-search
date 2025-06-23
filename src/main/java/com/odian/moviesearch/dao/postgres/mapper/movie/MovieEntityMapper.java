package com.odian.moviesearch.dao.postgres.mapper.movie;

import com.odian.moviesearch.core.model.Movie;
import com.odian.moviesearch.dao.postgres.model.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface MovieEntityMapper {
    default MovieEntity to (Movie movie){return null;}
    @Mapping(target = "unmappableField", ignore = true)
    default Movie to (MovieEntity movie){return null;}
}
