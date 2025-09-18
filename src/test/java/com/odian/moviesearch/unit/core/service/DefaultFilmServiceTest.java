package com.odian.moviesearch.unit.core.service;

import com.odian.moviesearch.core.application.exception.NotFoundException;
import com.odian.moviesearch.core.application.port.out.FilmRepository;
import com.odian.moviesearch.core.application.service.DefaultFilmService;
import com.odian.moviesearch.core.domain.model.Film;
import com.odian.moviesearch.unit.util.FilmUtils;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultFilmServiceTest {

//    private final static FilmRepository filmRepository = mock(FilmRepository.class);
//    private final static DefaultFilmService subject = new DefaultFilmService(filmRepository);
//
//    @Test
//    public void testSuccessfulFindFilmById () {
//        Film expected = FilmUtils.getFilm();
//        when(filmRepository.findById(any(UUID.class))).thenReturn(Optional.of(expected));
//        assertEquals(expected, subject.findById(UUID.randomUUID()));
//    }
//
//    @Test
//    public void testNotFoundFindFilmById () {
//        when(filmRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
//        assertThrows(NotFoundException.class, () -> subject.findById(UUID.randomUUID()));
//    }
}
