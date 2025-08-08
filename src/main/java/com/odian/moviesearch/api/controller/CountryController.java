package com.odian.moviesearch.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;
    private final CountryDTOMapper countryMapper;

    @GetMapping
    public Set<CountryDTO> findAll () {
        return countryMapper.domainToDto(countryService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> create (@RequestBody CountryDTO country) {
        countryService.create(country.name());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
