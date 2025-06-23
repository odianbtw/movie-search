package com.odian.moviesearch.api.model;

import com.odian.moviesearch.core.model.enums.MovieRole;

import java.time.LocalDate;
import java.util.List;

public record PersonDTO(
        Long id,
        String name,
        String biography,
        CountryDTO country,
        LocalDate birthDate,
        String photoUrl,
        List<MovieRole> movieRoles
) {
}
