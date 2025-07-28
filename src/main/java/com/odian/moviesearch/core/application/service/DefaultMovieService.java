package com.odian.moviesearch.core.application.service;

import com.odian.moviesearch.core.application.exception.NotFoundException;
import com.odian.moviesearch.core.application.model.Pageable;
import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.application.port.in.MovieService;
import com.odian.moviesearch.core.application.port.out.MovieRepository;
import com.odian.moviesearch.core.domain.model.Movie;
import com.odian.moviesearch.core.domain.model.Title;
import com.odian.moviesearch.core.domain.model.TitleCredit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Title findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie with this id not found"));
    }

    @Transactional
    @Override
    public Title create(Title title) {
        return movieRepository.create((Movie) title);
    }

    @Override
    public PagedResponse<Movie> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

}
