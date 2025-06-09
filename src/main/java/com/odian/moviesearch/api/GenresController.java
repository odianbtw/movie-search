package com.odian.moviesearch.api;


import com.odian.moviesearch.dto.GenreDTO;
import com.odian.moviesearch.services.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
@AllArgsConstructor
public class GenresController {

    private final GenreService genreService;

    @GetMapping
    public List<GenreDTO> findAll () {
        return genreService.findAll();
    }

}
