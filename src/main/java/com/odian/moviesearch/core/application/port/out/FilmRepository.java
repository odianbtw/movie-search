package com.odian.moviesearch.core.application.port.out;

import com.odian.moviesearch.core.domain.model.Film;

import java.util.Optional;
import java.util.UUID;

public interface FilmRepository {
    Optional<Film> findById (UUID id);
}
