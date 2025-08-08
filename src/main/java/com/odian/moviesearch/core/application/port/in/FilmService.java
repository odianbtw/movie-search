package com.odian.moviesearch.core.application.port.in;

import com.odian.moviesearch.core.domain.model.Film;

import java.util.UUID;

public interface FilmService {
    Film findById (UUID id);
}
