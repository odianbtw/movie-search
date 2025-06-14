package com.odian.moviesearch.api.model;

public record CompanyDTO(
        Long id,
        String name,
        String logoUrl,
        CountryDTO country,
        String description
) {
}
