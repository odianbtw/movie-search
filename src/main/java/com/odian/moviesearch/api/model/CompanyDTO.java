package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

public record CompanyDTO(
        @NotNull Long id,
        @NotNull String name,
        @NotNull String logoUrl,
        @NotNull CountryDTO country,
        @NotNull String description
) {
}
