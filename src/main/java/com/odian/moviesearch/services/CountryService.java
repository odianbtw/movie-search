package com.odian.moviesearch.services;

import com.odian.moviesearch.dto.CountryDTO;
import com.odian.moviesearch.model.Country;
import com.odian.moviesearch.repositories.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<CountryDTO> findAll () {
        return countryRepository.findAll().stream()
                .map(country -> new CountryDTO(country.getId(), country.getName()))
                .toList();
    }

    protected static CountryDTO wrapper (Country country) {
        return new CountryDTO(
                country.getId(),
                country.getName()
        );
    }
}
