package com.odian.moviesearch.core.services;


import com.odian.moviesearch.core.dao.MovieDao;
import com.odian.moviesearch.core.exceptions.NotFoundException;
import com.odian.moviesearch.core.model.Movie;
import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.utils.Pageable;
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

    @Override
    public Movie findById(Long id) {
        return movieDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie with this id not found"));
    }

    @Override
    public PagedResponse<Movie> findAll(Pageable pageable) {

    }
}
