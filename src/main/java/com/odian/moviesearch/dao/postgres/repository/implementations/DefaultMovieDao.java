package com.odian.moviesearch.dao.postgres.repository.implementations;

import com.odian.moviesearch.core.dao.MovieDao;
import com.odian.moviesearch.core.model.Movie;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultMovieDao implements MovieDao {
    @Override
    public Movie create(Movie movie) {
        return null;
    }
}
