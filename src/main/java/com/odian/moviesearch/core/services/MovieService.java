package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.MovieDao;
import com.odian.moviesearch.core.exceptions.NotFoundException;
import com.odian.moviesearch.core.model.Movie;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MovieService {
    private final MovieDao movieDao;

    /**
    returns list of movies that starts with ${name} or movie that named as ${name}
     */
    public List<Movie> findByName (String name) {
        return movieDao.findByName(name);
    }

    private Movie findById (Long id) {
        return movieDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie with this id not found"));
    }

    

}
