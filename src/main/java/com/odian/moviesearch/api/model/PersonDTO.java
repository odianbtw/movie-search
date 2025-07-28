package com.odian.moviesearch.api.model;

public record PersonDTO(
        Long id,
        String imdbId,
        String name,
        String biography,
        CountryDTO country,
        String photoUrl
) {
}
