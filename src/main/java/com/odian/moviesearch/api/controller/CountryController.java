package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.CountryDTOMapper;
import com.odian.moviesearch.api.model.CountryDTO;
import com.odian.moviesearch.core.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;
    private final CountryDTOMapper mapper;

    @GetMapping
    public List<CountryDTO> findAll () {
        return mapper.to(service.findAll());
    }
}
