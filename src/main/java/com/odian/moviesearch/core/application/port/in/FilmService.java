package com.odian.moviesearch.core.application.port.in;

import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.application.model.RequestCriteria;
import com.odian.moviesearch.core.domain.model.Film;

import java.util.UUID;

public interface FilmService {
    Film findById (UUID id);
    Film save (Film film);
    void delete (UUID id);
    void update (Film film);
    PagedResponse<Film> findAll(RequestCriteria criteria);
}
