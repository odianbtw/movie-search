package com.odian.moviesearch.unit.api.controllers;

import com.odian.moviesearch.api.controller.GenreController;
import com.odian.moviesearch.api.mapper.GenreDTOMapper;
import com.odian.moviesearch.api.model.GenreDTO;
import com.odian.moviesearch.core.model.Genre;
import com.odian.moviesearch.core.services.GenreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GenreController.class)
public class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GenreService genreService;
    @MockitoBean
    private GenreDTOMapper genreDTOMapper;

    @Test
    void testFindAllNotEmpty () throws Exception {
        Genre genre = new Genre(1, "Thriller");
        GenreDTO genreDTO = new GenreDTO(1, "Thriller");

        when(genreService.findAll()).thenReturn(List.of(genre));
        when(genreDTOMapper.to(anyList())).thenReturn(List.of(genreDTO));

        mockMvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(genreDTO.id()))
                .andExpect(jsonPath("$[0].name").value(genreDTO.name()));
    }

    @Test
    void testFindAllEmpty () throws Exception {
        when(genreService.findAll()).thenReturn(List.of());
        when(genreDTOMapper.to(anyList())).thenReturn(List.of());
        mockMvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));
    }

}
