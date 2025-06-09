package com.odian.moviesearch.dto;

import java.time.LocalDate;

public record PersonDTO(
    Long id,
    String name,
    String biography,
    CountryDTO country,
    LocalDate birthDate
) {
}
