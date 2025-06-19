package com.odian.moviesearch.core.services;


import com.odian.moviesearch.core.dao.MovieDao;
import com.odian.moviesearch.core.model.Movie;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    private final MovieDao movieDao;

    @Transactional
    @Override
    public Movie create(Movie movie) {
        return movieDao.create(movie);
    }
}
