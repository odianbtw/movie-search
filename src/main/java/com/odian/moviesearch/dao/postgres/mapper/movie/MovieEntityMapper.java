package com.odian.moviesearch.dao.postgres.mapper.movie;

import com.odian.moviesearch.core.model.Movie;
import com.odian.moviesearch.dao.postgres.model.MovieEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieEntityMapper {
    MovieEntity to (Movie movie);
    Movie to (MovieEntity movie);
}
