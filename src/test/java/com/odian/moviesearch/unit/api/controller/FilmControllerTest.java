package com.odian.moviesearch.unit.api.controller;


import com.odian.moviesearch.api.controller.FilmController;
import com.odian.moviesearch.api.mapper.FilmDTOMapper;
import com.odian.moviesearch.api.model.FilmDTO;
import com.odian.moviesearch.core.application.exception.NotFoundException;
import com.odian.moviesearch.core.application.port.in.FilmService;
import com.odian.moviesearch.core.domain.model.Film;
import com.odian.moviesearch.unit.util.FilmUtils;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilmControllerTest {

    private static final FilmService filmService = mock(FilmService.class);
    private static final FilmDTOMapper filmDTOMapper = mock(FilmDTOMapper.class);
    private static final FilmController subject = new FilmController(filmService, filmDTOMapper);

    @Test
    public void testSuccessfulFindFilmById () {
        Film film = FilmUtils.getFilm();
        FilmDTO expected = FilmUtils.createDto(film);
        when(filmService.findById(any(UUID.class))).thenReturn(film);
        when(filmDTOMapper.domainToDto(any(Film.class))).thenReturn(expected);

        assertEquals(expected, subject.findById(UUID.randomUUID()));
    }

    @Test
    public void testNotFoundFindFilmById () {
        when(filmService.findById(any(UUID.class))).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> subject.findById(UUID.randomUUID()));
    }

}
