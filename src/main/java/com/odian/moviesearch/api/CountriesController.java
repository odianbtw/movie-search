package com.odian.moviesearch.api;


import com.odian.moviesearch.dto.CountryDTO;
import com.odian.moviesearch.services.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountriesController {

    private final CountryService countryService;

    @GetMapping
    public List<CountryDTO> findAll () {
        return countryService.findAll();
    }

}
