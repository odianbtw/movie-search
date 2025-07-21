package com.odian.moviesearch.core.application.service;

import com.odian.moviesearch.core.application.port.in.MovieService;
import com.odian.moviesearch.core.application.port.out.MovieRepository;
import com.odian.moviesearch.core.domain.model.Movie;
import com.odian.moviesearch.core.domain.model.Title;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Title create(Title title) {
        movieRepository.create((Movie) title);
    }
}
