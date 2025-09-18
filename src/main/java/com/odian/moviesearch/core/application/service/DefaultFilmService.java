package com.odian.moviesearch.core.application.service;

import com.odian.moviesearch.core.application.exception.NotFoundException;
import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.application.model.RequestCriteria;
import com.odian.moviesearch.core.application.port.in.FilmService;
import com.odian.moviesearch.core.application.port.in.KeywordService;
import com.odian.moviesearch.core.application.port.in.MediaService;
import com.odian.moviesearch.core.application.port.out.FilmRepository;
import com.odian.moviesearch.core.domain.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultFilmService implements FilmService {

    private final FilmRepository filmRepository;
    private final MediaService mediaService;
    private final KeywordService keywordService;


    @Override
    public Film findById(UUID id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Film with this id not found"));
    }

    @Override
    @Transactional
    public Film save(Film film) {
        if (film.getId() == null) {
            film.setId(UUID.randomUUID());
        }
        prepareFilm(film);
        return filmRepository.save(film);
    }

    @Override
    public void delete(UUID id) {
        filmRepository.delete(id);
    }

    @Override
    @Transactional
    public void update(Film film) {
        prepareFilm(film);
        filmRepository.update(film);
    }

    @Override
    public PagedResponse<Film> findAll(RequestCriteria criteria) {
        return filmRepository.findAll(criteria);
    }


    private void prepareFilm (Film film) {
        film.setSlug(SlugGenerator.generateSlug(film.getId(), film.getName()));
        var medias = mediaService.saveAll(film.getDetails().getMedias());
        film.getDetails().setMedias(medias);
        var keywords = keywordService.saveNewKeywords(film.getDetails().getKeywords());
        film.getDetails().setKeywords(keywords);
    }


}
