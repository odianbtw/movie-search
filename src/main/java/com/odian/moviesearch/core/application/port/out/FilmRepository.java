package com.odian.moviesearch.core.application.port.out;

import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.application.model.RequestCriteria;
import com.odian.moviesearch.core.domain.model.Film;

import java.util.Optional;
import java.util.UUID;

public interface FilmRepository {
    Optional<Film> findById (UUID id);
    Film save (Film film);
    void delete(UUID id);
    void update (Film film);
    PagedResponse<Film> findAll(RequestCriteria criteria);
}
