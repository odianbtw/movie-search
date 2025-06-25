package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

public record CompanyUpdateDTO (
        @NotNull Long id,
        @NotNull String name,
        @NotNull String logoUrl,
        @NotNull Integer countryId,
        @NotNull String description
) {
}
