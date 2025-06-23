package com.odian.moviesearch.api.model;


import java.time.LocalDate;

public record PersonDTO(
        Long id,
        String name,
        String biography,
        CountryDTO country,
        LocalDate birthDate,
        String photoUrl
) {
}
