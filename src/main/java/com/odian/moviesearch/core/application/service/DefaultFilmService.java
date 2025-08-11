package com.odian.moviesearch.core.application.service;

import com.odian.moviesearch.core.application.exception.NotFoundException;
import com.odian.moviesearch.core.application.port.in.FilmService;
import com.odian.moviesearch.core.application.port.out.FilmRepository;
import com.odian.moviesearch.core.domain.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultFilmService implements FilmService {

    private final FilmRepository filmRepository;

    @Override
    public Film findById(UUID id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Film with this id not found"));
    }
}
